package com.test.application.core.repository

import com.test.application.core.domain.contacts.ContactsItem

interface ContactsRepository {
    fun getAllContacts() : List<ContactsItem>
}