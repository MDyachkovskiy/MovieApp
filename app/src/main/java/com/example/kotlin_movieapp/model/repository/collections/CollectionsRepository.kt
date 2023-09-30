package com.example.kotlin_movieapp.model.repository.collections

import com.example.kotlin_movieapp.model.AppState.AppState

interface CollectionsRepository {
    suspend fun getTop250CollectionFromServer(): AppState
    suspend fun getTopTvShowsCollectionFromServer(): AppState
    suspend fun getUpComingCollectionFromServer(): AppState
}