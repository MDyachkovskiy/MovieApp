package com.example.kotlin_movieapp.view.personDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.application.core.utils.AppState.AppState
import com.example.kotlin_movieapp.model.repository.personDetails.PersonDetailsRepository
import kotlinx.coroutines.launch

class PersonDetailsViewModel (
    private val personRepository: PersonDetailsRepository
) : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData() = liveData

    fun getPersonDetailsFromRemoteSource(personId: Int?) {
        viewModelScope.launch {
            liveData.value = AppState.Loading
            liveData.value = personRepository.getPersonDetailsFromRemoteServer(personId)
        }
    }
}