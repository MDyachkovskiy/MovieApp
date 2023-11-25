package com.example.kotlin_movieapp.model.repository.personDetails

import android.util.Log
import com.test.application.core.utils.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import com.test.application.core.utils.REQUEST_ERROR

class PersonDetailsRepositoryImpl (
    private val remoteDataSource: RemoteDataSource
) : PersonDetailsRepository {

    override suspend fun getPersonDetailsFromRemoteServer(personId: Int?): AppState {
        return try {
            val response = remoteDataSource.getPersonDetails(personId)
            Log.d("@@@", "Person Details repository received response: $response")
            AppState.Success(response)
        } catch (e:Throwable) {
            Log.d("@@@", "Error receiving response: ${e.message}")
            AppState.Error(Throwable(e.message ?: REQUEST_ERROR))
        }
    }
}