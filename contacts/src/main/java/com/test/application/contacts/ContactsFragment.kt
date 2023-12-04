package com.test.application.contacts

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.application.core.utils.AppState.AppState
import com.test.application.core.utils.init
import com.test.application.contacts.databinding.FragmentContactsBinding
import com.test.application.contacts.utils.CONTACTS_REQUEST_CODE
import com.test.application.contacts.utils.checkPermission
import com.test.application.contacts.utils.showAlertMessage
import com.test.application.core.domain.contacts.ContactsItem
import com.test.application.core.view.BaseFragmentWithAppState
import org.koin.androidx.viewmodel.ext.android.viewModel


class ContactsFragment : BaseFragmentWithAppState<AppState, List<ContactsItem>, FragmentContactsBinding>(
    FragmentContactsBinding::inflate
) {

    private val contactsGetter by lazy {
        ContactsGetter(requireContext(), viewModel)
    }

    private val viewModel: ContactsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        if (checkPermission(Manifest.permission.READ_CONTACTS, activity, binding.root)) {
            getContacts()
        }

        Thread {
            viewModel.getAllContacts()
        }.start()
    }

    override fun setupData(data: List<ContactsItem>) {
        initRV(data)
    }

    private fun initRV(contactsData: List<ContactsItem>) {
        binding.rvContacts.init(ContactsAdapter(contactsData, requireActivity()),
            LinearLayoutManager.VERTICAL
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            CONTACTS_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContacts()

                } else {
                    binding.contactsFragment.showAlertMessage(CONTACTS_REQUEST_CODE, activity)
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun getContacts() {
        contactsGetter.getContacts()
    }
}