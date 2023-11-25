package com.example.kotlin_movieapp.model.repository.personDetails

import com.test.application.core.utils.AppState.AppState

interface PersonDetailsRepository {
    suspend fun getPersonDetailsFromRemoteServer (personId : Int?): AppState
}