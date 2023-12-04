package com.test.application.local_data.repository

import com.test.application.core.domain.contacts.ContactsItem
import com.test.application.core.repository.ContactsRepository
import com.test.application.local_data.contacts.ContactsDao
import com.test.application.local_data.mapper.toDomain

class ContactsRepositoryImpl (
    private val localDataSource : ContactsDao
) : ContactsRepository {

    override fun getAllContacts(): List<ContactsItem> {
        val contacts = localDataSource.getAllContacts().map {contactEntity ->
            contactEntity.toDomain()
        }
        return contacts
    }
}