package com.example.kotlin_movieapp.model.repository.movieDetails

import com.test.application.core.utils.AppState.AppState

interface DetailsRepository {
    suspend fun getMovieDetailsFromServer(movieId: Int?): AppState
}