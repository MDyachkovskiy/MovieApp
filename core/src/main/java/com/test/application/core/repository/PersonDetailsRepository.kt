package com.test.application.core.repository

import com.test.application.core.utils.AppState.AppState

interface PersonDetailsRepository {
    suspend fun getPersonDetailsFromRemoteServer (personId : Int): AppState
}