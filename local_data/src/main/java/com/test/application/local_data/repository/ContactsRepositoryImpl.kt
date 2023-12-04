package com.test.application.local_data.repository

import android.content.Context
import com.test.application.core.domain.contacts.ContactsItem
import com.test.application.core.repository.ContactsRepository
import com.test.application.local_data.contacts.ContactsDao
import com.test.application.local_data.contacts.ContactsGetter

class ContactsRepositoryImpl (
    private val context: Context,
    private val localDataSource : ContactsDao
) : ContactsRepository {

    private val contactsGetter = ContactsGetter(context)
    override fun getAllContacts(): List<ContactsItem> {
        return convertListContactsEntityToContactsItem(localDataSource.all())
    }

    override fun saveEntity(contact: ContactsItem) {
        val contactEntity = convertContactsItemToContactsEntity(contact)
        localDataSource.insert(contactEntity)
    }
}