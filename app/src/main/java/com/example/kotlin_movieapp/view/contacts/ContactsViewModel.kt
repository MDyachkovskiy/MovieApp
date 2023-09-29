package com.example.kotlin_movieapp.view.contacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movieapp.app.App
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.local.room.contacts.ContactsItem
import com.example.kotlin_movieapp.model.repository.contacts.ContactsRepositoryImpl

class ContactsViewModel(
    private val contactsLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val contactsRepository: ContactsRepositoryImpl =
        ContactsRepositoryImpl(App.getContactsDao())
) : ViewModel() {

    fun getLiveData() = contactsLiveData

    fun getAllContacts() {
        contactsLiveData.postValue(AppState.Loading)
        contactsLiveData.postValue(AppState.Success(contactsRepository.getAllContacts()))
    }

    fun addAllContacts(contacts: MutableList<ContactsItem>){
        contacts.forEach { entity -> contactsRepository.saveEntity(entity) }
    }
}