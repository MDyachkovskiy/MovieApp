package com.example.kotlin_movieapp.model.repository.personDetails

import com.example.kotlin_movieapp.model.AppState.AppState

interface PersonDetailsRepository {
    suspend fun getPersonDetailsFromRemoteServer (personId : Int?): AppState
}