package com.example.kotlin_movieapp.view.contacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.application.core.utils.AppState.AppState
import com.test.application.local_data.contacts.ContactsItem
import com.example.kotlin_movieapp.model.repository.contacts.ContactsRepository

class ContactsViewModel(
    private val contactsRepository: ContactsRepository
) : ViewModel() {

    private val contactsLiveData: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData() = contactsLiveData

    fun getAllContacts() {
        contactsLiveData.postValue(AppState.Loading)
        contactsLiveData.postValue(AppState.Success(contactsRepository.getAllContacts()))
    }

    fun addAllContacts(contacts: MutableList<com.test.application.local_data.contacts.ContactsItem>){
        contacts.forEach { entity -> contactsRepository.saveEntity(entity) }
    }
}