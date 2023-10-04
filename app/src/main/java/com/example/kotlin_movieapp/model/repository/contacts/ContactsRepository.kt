package com.example.kotlin_movieapp.model.repository.contacts

import com.example.kotlin_movieapp.model.datasource.local.room.contacts.ContactsItem

interface ContactsRepository {
    fun getAllContacts() : List<ContactsItem>
    fun saveEntity(contact: ContactsItem)
}