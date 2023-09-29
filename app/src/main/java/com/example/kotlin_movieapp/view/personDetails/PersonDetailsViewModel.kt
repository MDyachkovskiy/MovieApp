package com.example.kotlin_movieapp.view.personDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movieapp.model.AppState.DetailsState
import com.example.kotlin_movieapp.model.datasource.remote.personDetailsResponse.PersonDTO
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import com.example.kotlin_movieapp.model.repository.personDetails.PersonDetailsRepository
import com.example.kotlin_movieapp.model.repository.personDetails.PersonDetailsRepositoryImpl
import com.example.kotlin_movieapp.utils.REQUEST_ERROR
import com.example.kotlin_movieapp.utils.SERVER_ERROR
import retrofit2.Call
import retrofit2.Response

class PersonDetailsViewModel (
    private val liveData: MutableLiveData<DetailsState> = MutableLiveData(),
    private val personRepository: PersonDetailsRepository =
            PersonDetailsRepositoryImpl(RemoteDataSource())
) : ViewModel() {

    private val callback = object: retrofit2.Callback<PersonDTO> {
        override fun onResponse(
            call: Call<PersonDTO>,
            response: Response<PersonDTO>) {
            val serverResponse: PersonDTO? = response.body()
            liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    DetailsState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<PersonDTO>, t: Throwable) {
            liveData.postValue(DetailsState.Error(Throwable(t.message?: REQUEST_ERROR)))
        }
    }

    private fun checkResponse(serverResponse: PersonDTO): DetailsState {
        return DetailsState.SuccessPerson(serverResponse)
    }

    fun getLiveData() = liveData

    fun getPersonDetailsFromRemoteSource(personId: Int?) {
        liveData.value = DetailsState.Loading
        personRepository.getPersonDetailsFromRemoteServer(personId, callback)
    }

}