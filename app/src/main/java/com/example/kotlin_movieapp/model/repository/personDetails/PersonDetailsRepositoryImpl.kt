package com.example.kotlin_movieapp.model.repository.personDetails

import com.example.kotlin_movieapp.model.datasource.remote.personDetailsResponse.PersonDTO
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import retrofit2.Callback

class PersonDetailsRepositoryImpl (
    private val remoteDataSource: RemoteDataSource
) : PersonDetailsRepository {

    override fun getPersonDetailsFromRemoteServer(personId: Int?, callback: Callback<PersonDTO>) {
        remoteDataSource.getPersonDetails(personId, callback)
    }
}