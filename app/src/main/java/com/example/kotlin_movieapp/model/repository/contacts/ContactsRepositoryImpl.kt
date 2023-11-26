package com.example.kotlin_movieapp.model.repository.contacts

import com.test.application.local_data.contacts.ContactsDao
import com.test.application.local_data.contacts.ContactsItem
import com.example.kotlin_movieapp.utils.convertContactsItemToContactsEntity
import com.example.kotlin_movieapp.utils.convertListContactsEntityToContactsItem

class ContactsRepositoryImpl (
    private val localDataSource : com.test.application.local_data.contacts.ContactsDao
) : ContactsRepository {
    override fun getAllContacts(): List<com.test.application.local_data.contacts.ContactsItem> {
        return convertListContactsEntityToContactsItem(localDataSource.all())
    }

    override fun saveEntity(contact: com.test.application.local_data.contacts.ContactsItem) {
        val contactEntity = convertContactsItemToContactsEntity(contact)
        localDataSource.insert(contactEntity)
    }
}