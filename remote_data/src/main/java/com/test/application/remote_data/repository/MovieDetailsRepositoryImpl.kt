package com.test.application.remote_data.repository

import com.test.application.core.utils.AppState.AppState
import com.test.application.core.repository.MovieDetailsRepository
import com.test.application.core.utils.REQUEST_ERROR
import com.test.application.remote_data.api.KinopoiskService


class MovieDetailsRepositoryImpl (
    private val kinopoiskService: KinopoiskService
) : MovieDetailsRepository {

    override suspend fun getMovieDetailsFromServer(movieId: Int): AppState {
        return try {
            val id = movieId.toString()
            val response = kinopoiskService.getMovieAsync(id = id)
            AppState.Success(response)
        } catch (e: Throwable) {
            AppState.Error(Throwable(e.message ?: REQUEST_ERROR))
        }
    }
}