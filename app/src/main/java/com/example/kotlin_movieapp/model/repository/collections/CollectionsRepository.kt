package com.example.kotlin_movieapp.model.repository.collections

import androidx.paging.PagingData
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.domain.collection.Doc
import kotlinx.coroutines.flow.Flow

interface CollectionsRepository {
    fun getTop250CollectionFromServer(): Flow<PagingData<Doc>>
    suspend fun getTopTvShowsCollectionFromServer(): AppState
    suspend fun getUpComingCollectionFromServer(): AppState
}