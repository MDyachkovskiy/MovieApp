package com.example.kotlin_movieapp.model.AppState

sealed class AppState {
    object Loading : AppState()

    data class Success<T>(val data: T) : AppState()

    data class Error(val error : Throwable) : AppState()
}