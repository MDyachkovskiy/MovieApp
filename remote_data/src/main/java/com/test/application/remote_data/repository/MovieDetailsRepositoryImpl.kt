package com.test.application.remote_data.repository

import com.test.application.core.utils.AppState.AppState
import com.test.application.core.repository.MovieDetailsRepository
import com.test.application.core.utils.REQUEST_ERROR
import com.test.application.remote_data.api.KinopoiskService
import com.test.application.remote_data.mapper.toDomain


class MovieDetailsRepositoryImpl (
    private val kinopoiskService: KinopoiskService
) : MovieDetailsRepository {

    override suspend fun getMovieDetailsFromServer(movieId: Int): AppState {
        return try {
            val id = movieId.toString()
            val response = kinopoiskService.getMovieAsync(id = id).await()
            AppState.Success(response.toDomain())
        } catch (e: Throwable) {
            AppState.Error(Throwable(e.message ?: REQUEST_ERROR))
        }
    }
}