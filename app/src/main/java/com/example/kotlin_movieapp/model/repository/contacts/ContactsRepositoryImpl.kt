package com.example.kotlin_movieapp.model.repository.contacts

import com.example.kotlin_movieapp.model.datasource.local.room.contacts.ContactsDao
import com.example.kotlin_movieapp.model.datasource.local.room.contacts.ContactsItem
import com.example.kotlin_movieapp.utils.convertContactsItemToContactsEntity
import com.example.kotlin_movieapp.utils.convertListContactsEntityToContactsItem

class ContactsRepositoryImpl (
    private val localDataSource : ContactsDao
) : ContactsRepository {
    override fun getAllContacts(): List<ContactsItem> {
        return convertListContactsEntityToContactsItem(localDataSource.all())
    }

    override fun saveEntity(contact: ContactsItem) {
        val contactEntity = convertContactsItemToContactsEntity(contact)
        localDataSource.insert(contactEntity)
    }

}