package com.example.kotlin_movieapp.view.personDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.remote.personDetailsResponse.PersonDTO
import com.example.kotlin_movieapp.model.repository.personDetails.PersonDetailsRepository
import com.example.kotlin_movieapp.utils.REQUEST_ERROR
import com.example.kotlin_movieapp.utils.SERVER_ERROR
import retrofit2.Call
import retrofit2.Response

class PersonDetailsViewModel (
    private val personRepository: PersonDetailsRepository
) : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()

    private val callback = object: retrofit2.Callback<PersonDTO> {
        override fun onResponse(
            call: Call<PersonDTO>,
            response: Response<PersonDTO>) {
            val serverResponse: PersonDTO? = response.body()
            liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<PersonDTO>, t: Throwable) {
            liveData.postValue(AppState.Error(Throwable(t.message?: REQUEST_ERROR)))
        }
    }

    private fun checkResponse(serverResponse: PersonDTO): AppState {
        return AppState.Success(serverResponse)
    }

    fun getLiveData() = liveData

    fun getPersonDetailsFromRemoteSource(personId: Int?) {
        liveData.value = AppState.Loading
        personRepository.getPersonDetailsFromRemoteServer(personId, callback)
    }
}