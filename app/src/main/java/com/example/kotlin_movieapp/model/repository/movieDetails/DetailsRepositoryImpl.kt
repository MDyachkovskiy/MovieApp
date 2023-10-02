package com.example.kotlin_movieapp.model.repository.movieDetails

import android.util.Log
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import com.example.kotlin_movieapp.utils.REQUEST_ERROR


class DetailsRepositoryImpl (
    private val remoteDataSource: RemoteDataSource
) : DetailsRepository {

    override suspend fun getMovieDetailsFromServer(movieId: Int?): AppState {
        return try {
            val response = remoteDataSource.getMovieDetails(movieId)
            Log.d("@@@", "Details repository received response: $response")
            AppState.Success(response)
        } catch (e: Throwable) {
            Log.d("@@@", "Error receiving response: ${e.message}")
            AppState.Error(Throwable(e.message ?: REQUEST_ERROR))
        }
    }
}