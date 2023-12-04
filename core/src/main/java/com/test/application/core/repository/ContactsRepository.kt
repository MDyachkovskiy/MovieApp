package com.test.application.core.repository

import com.test.application.local_data.contacts.ContactsItem

interface ContactsRepository {
    fun getAllContacts() : List<com.test.application.local_data.contacts.ContactsItem>
    fun saveEntity(contact: com.test.application.local_data.contacts.ContactsItem)
}