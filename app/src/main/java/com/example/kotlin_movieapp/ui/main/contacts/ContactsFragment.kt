package com.example.kotlin_movieapp.ui.main.contacts

import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_movieapp.adapters.ContactsAdapter
import com.example.kotlin_movieapp.databinding.FragmentContactsBinding
import com.example.kotlin_movieapp.model.room.contacts.ContactsItem
import com.example.kotlin_movieapp.ui.main.AppState.AppState
import com.example.kotlin_movieapp.ui.main.AppState.AppStateRenderer
import com.example.kotlin_movieapp.utils.CONTACTS_REQUEST_CODE
import com.example.kotlin_movieapp.utils.init
import com.example.kotlin_movieapp.utils.requestContactsPermission
import com.example.kotlin_movieapp.utils.showAlertMessageContacts

class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!

    private val dataRenderer by lazy { AppStateRenderer(binding) }

    private val viewModel: ContactsViewModel by lazy {
        ViewModelProvider (this)[ContactsViewModel::class.java]
    }

    companion object {
        fun newInstance() = ContactsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkPermission()

        Thread {
            viewModel.getAllContacts()
        }.start()

        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    private fun renderData(appState: AppState) {
        dataRenderer.render(appState)

        when(appState) {
            is AppState.SuccessContacts -> {
                initRV (appState.contactsData)
            }
            else -> return
        }
    }

    private fun initRV(contactsData: List<ContactsItem>) {
        binding.contactsRecyclerView.init(ContactsAdapter(contactsData, activity),
            LinearLayoutManager.VERTICAL)
    }

    private fun checkPermission() {
        context?.let {

            when {
                ContextCompat.checkSelfPermission(it, Manifest.permission.READ_CONTACTS) 
                        == PackageManager.PERMISSION_GRANTED -> {
                        getContacts()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) -> {
                    binding.contactsFragment.showAlertMessageContacts(activity)
                }
                else -> {
                    requestContactsPermission(activity)
                }
            }
        }
    }

    private fun getContacts() {
        context?.let{

            Thread {
                val contentResolver : ContentResolver = it.contentResolver

                val cursorWithContacts : Cursor? = contentResolver.query (
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    null,
                    null,
                    ContactsContract.Contacts._ID + " ASC")

                cursorWithContacts?.let { cursor ->
                    for (i in 0 until cursor.count) {

                        val columnIdIndex = cursor.getColumnIndex(ContactsContract
                            .CommonDataKinds
                            .Phone
                            ._ID)

                        val columnNameIndex =
                            cursor.getColumnIndex(ContactsContract
                                .CommonDataKinds
                                .Phone
                                .DISPLAY_NAME)

                        val columnPhoneNumberIndex =
                            cursor.getColumnIndex(ContactsContract
                                .CommonDataKinds
                                .Phone
                                .NUMBER)

                        if (cursor.moveToPosition(i)) {
                            val id = cursor.getString(columnIdIndex)
                            val name = cursor.getString(columnNameIndex)
                            val phoneNumber = cursor.getString(columnPhoneNumberIndex)
                            val contact = ContactsItem(id, name, phoneNumber)
                            viewModel.addContact(contact)
                        }
                    }
                }
                cursorWithContacts?.close()
            }.start()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            CONTACTS_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getContacts()
                } else {
                    binding.contactsFragment.showAlertMessageContacts(activity)
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}