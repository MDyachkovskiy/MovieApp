package com.test.application.contacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.application.core.domain.contacts.ContactsItem
import com.test.application.core.repository.ContactsRepository
import com.test.application.core.utils.AppState.AppState

class ContactsViewModel(
    private val contactsRepository: ContactsRepository
) : ViewModel() {

    private val contactsLiveData: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData() = contactsLiveData

    fun getAllContacts() {
        contactsLiveData.postValue(AppState.Loading)
        contactsLiveData.postValue(AppState.Success(contactsRepository.getAllContacts()))
    }

    fun addAllContacts(contacts: MutableList<ContactsItem>){
        contacts.forEach { entity -> contactsRepository.saveEntity(entity) }
    }
}