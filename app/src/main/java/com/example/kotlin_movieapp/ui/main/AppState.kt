package com.example.kotlin_movieapp.ui.main

import com.example.kotlin_movieapp.models.Top250Response

sealed class AppState {
    object Loading : AppState()
    data class Success(val movieData : Top250Response) : AppState()
    data class Error(val error : Throwable) : AppState()
}