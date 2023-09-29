package com.example.kotlin_movieapp.model.repository.personDetails

import com.example.kotlin_movieapp.model.datasource.remote.personDetailsResponse.PersonDTO

interface PersonDetailsRepository {
    fun getPersonDetailsFromRemoteServer (personId : Int?, callback: retrofit2.Callback<PersonDTO>)
}