package com.test.application.core.repository

import com.test.application.core.utils.AppState.AppState

interface MovieDetailsRepository {
    suspend fun getMovieDetailsFromServer(movieId: Int): AppState
}