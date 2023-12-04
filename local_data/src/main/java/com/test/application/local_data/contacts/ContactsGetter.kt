package com.test.application.local_data.contacts

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import com.test.application.core.domain.contacts.ContactsItem
import com.test.application.local_data.mapper.toEntity

class ContactsGetter (
    private val context : Context,
    private val contactsDao: ContactsDao
) {

    private val appContext = context.applicationContext

    fun fetchAndStoreContacts() {
        val contacts = getContactsfromContentProvider()
        contacts.map { contactItem ->
            contactsDao.insertContact(contactItem.toEntity())
        }
    }

    private fun getContactsfromContentProvider(): List<ContactsItem> {
        val contacts = mutableListOf<ContactsItem>()
        val contentResolver : ContentResolver = appContext.contentResolver

        val cursorWithContacts : Cursor? = contentResolver.query (
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.Contacts._ID + " ASC")

        cursorWithContacts?.let { cursor ->
            while (cursor.moveToNext()) {
                val idIndex = cursor.getColumnIndex(ContactsContract
                    .CommonDataKinds
                    .Phone
                    ._ID)

                val nameIndex = cursor.getColumnIndex(ContactsContract
                    .CommonDataKinds
                    .Phone
                    .DISPLAY_NAME)

                val phoneNumberIndex = cursor.getColumnIndex(ContactsContract
                    .CommonDataKinds
                    .Phone
                    .NUMBER)

                val id = cursor.getString(idIndex)
                val name = cursor.getString(nameIndex)
                val phoneNumber = cursor.getString(phoneNumberIndex)
                contacts.add(ContactsItem(id, name, phoneNumber))
            }
        }
        return contacts
    }
}