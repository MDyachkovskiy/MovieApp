package com.example.kotlin_movieapp.view.contacts

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_movieapp.databinding.FragmentContactsBinding
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.local.room.contacts.ContactsGetter
import com.example.kotlin_movieapp.model.datasource.local.room.contacts.ContactsItem
import com.example.kotlin_movieapp.utils.CONTACTS_REQUEST_CODE
import com.example.kotlin_movieapp.utils.checkPermission
import com.example.kotlin_movieapp.utils.init
import com.example.kotlin_movieapp.utils.showAlertMessage
import com.example.kotlin_movieapp.view.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class ContactsFragment : BaseFragment<AppState, List<ContactsItem>, FragmentContactsBinding>(
    FragmentContactsBinding::inflate
) {

    private val contactsGetter by lazy { ContactsGetter(context, viewModel) }

    private val viewModel: ContactsViewModel by viewModel()

    companion object {
        fun newInstance() = ContactsFragment()
    }

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
        binding.contactsRecyclerView.init(
            ContactsAdapter(
                contactsData,
                activity
            ),
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