package com.test.application.person_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.application.core.repository.PersonDetailsRepository
import com.test.application.core.utils.AppState.AppState
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