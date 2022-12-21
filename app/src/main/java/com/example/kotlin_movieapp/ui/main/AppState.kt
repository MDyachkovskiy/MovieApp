package com.example.kotlin_movieapp.ui.main

import com.example.kotlin_movieapp.models.Movie

sealed class AppState {
    object Loading : AppState()
    data class Success(val movieData : List<Movie>) : AppState()
    data class Error(val error : Throwable) : AppState()
}