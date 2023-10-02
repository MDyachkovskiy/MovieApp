package com.example.kotlin_movieapp.model.repository.movieDetails

import com.example.kotlin_movieapp.model.AppState.AppState

interface DetailsRepository {
    suspend fun getMovieDetailsFromServer(movieId: Int?): AppState
}