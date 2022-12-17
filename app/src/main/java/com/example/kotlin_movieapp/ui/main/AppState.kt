package com.example.kotlin_movieapp.ui.main

import com.example.kotlin_movieapp.models.MovieSource

sealed class AppState {
    object Loading: AppState()
    data class Success(Any()): AppState()
    data class Error(val error: Throwable): AppState()
}