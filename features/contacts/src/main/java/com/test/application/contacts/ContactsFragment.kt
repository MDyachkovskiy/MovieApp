package com.test.application.contacts

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.application.core.utils.AppState.AppState
import com.test.application.contacts.databinding.FragmentContactsBinding
import com.test.application.contacts.utils.CONTACTS_REQUEST_CODE
import com.test.application.contacts.utils.checkPermission
import com.test.application.contacts.utils.showAlertMessage
import com.test.application.core.domain.contacts.ContactsItem
import com.test.application.core.view.BaseFragmentWithAppState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContactsFragment : BaseFragmentWithAppState<AppState, List<ContactsItem>, FragmentContactsBinding>(
    FragmentContactsBinding::inflate
) {

    private val viewModel: ContactsViewModel by viewModels()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if(isGranted) {
            viewModel.fetchAllContacts()
        } else {
            binding.contactsFragment.showAlertMessage(CONTACTS_REQUEST_CODE, requireActivity())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkAndRequestPermission()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    private fun checkAndRequestPermission() {
        if (checkPermission(Manifest.permission.READ_CONTACTS, requireActivity(), binding.root)) {
            viewModel.fetchAllContacts()
        } else {
            requestPermissionLauncher.launch(
                Manifest.permission.READ_CONTACTS
            )
        }
    }

    override fun setupData(data: List<ContactsItem>) {
        val recyclerView = binding.rvContacts
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val contactsAdapter = ContactsAdapter()
        contactsAdapter.updateData(data)
        recyclerView.adapter = contactsAdapter

        contactsAdapter.listener = { phoneNumber ->
            if (checkPermission(Manifest.permission.CALL_PHONE, requireActivity(), binding.root)) {
                makePhoneCall(phoneNumber)
            }
        }
    }

    private fun makePhoneCall(phoneNumber: String) {
        if (phoneNumber.isNotEmpty()) {
            try {
                val callIntent = Intent(Intent.ACTION_DIAL)
                callIntent.data = Uri.parse("tel:$phoneNumber")
                context?.startActivity(callIntent)
            } catch(e: Exception) {
                showError(e)
            }
        }
    }

    private fun showError(e: Exception) {
        Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT)
            .show()
    }
}