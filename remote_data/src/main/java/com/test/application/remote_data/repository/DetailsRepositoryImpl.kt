package com.test.application.remote_data.repository

import com.test.application.core.utils.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import com.test.application.core.repository.MovieDetailsRepository
import com.test.application.core.utils.REQUEST_ERROR


class DetailsRepositoryImpl (
    private val remoteDataSource: RemoteDataSource
) : MovieDetailsRepository {

    override suspend fun getMovieDetailsFromServer(movieId: Int?): AppState {
        return try {
            val response = remoteDataSource.getMovieDetails(movieId)
            AppState.Success(response)
        } catch (e: Throwable) {
            AppState.Error(Throwable(e.message ?: REQUEST_ERROR))
        }
    }
}