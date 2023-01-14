package com.example.kotlin_movieapp.ui.main

import com.example.kotlin_movieapp.models.CollectionItem

sealed class AppState {
    object Loading : AppState()
    data class Success(val movieData : List<CollectionItem>) : AppState()
    data class Error(val error : Throwable) : AppState()
}