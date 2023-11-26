package com.example.kotlin_movieapp.model.repository.contacts

import com.test.application.local_data.contacts.ContactsItem

interface ContactsRepository {
    fun getAllContacts() : List<com.test.application.local_data.contacts.ContactsItem>
    fun saveEntity(contact: com.test.application.local_data.contacts.ContactsItem)
}