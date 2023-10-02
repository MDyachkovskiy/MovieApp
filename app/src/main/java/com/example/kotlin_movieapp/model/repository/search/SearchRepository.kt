package com.example.kotlin_movieapp.model.repository.search

import com.example.kotlin_movieapp.model.AppState.AppState

interface SearchRepository {
    suspend fun getSearchCollection(name: String): AppState
}