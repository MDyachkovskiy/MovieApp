package com.test.application.person_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.application.core.repository.PersonDetailsRepository
import com.test.application.core.utils.AppState.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    private val personRepository: PersonDetailsRepository
) : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData() = liveData

    fun getPersonDetailsFromRemoteSource(personId: Int) {
        viewModelScope.launch {
            liveData.value = AppState.Loading
            liveData.value = personRepository.getPersonDetailsFromRemoteServer(personId)
        }
    }
}