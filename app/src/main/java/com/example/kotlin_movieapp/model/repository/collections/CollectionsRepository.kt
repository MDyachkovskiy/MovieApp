package com.example.kotlin_movieapp.model.repository.collections

import androidx.paging.PagingData
import com.example.kotlin_movieapp.model.datasource.domain.collection.Movie
import kotlinx.coroutines.flow.Flow

interface CollectionsRepository {
    fun getTop250CollectionFromServer(): Flow<PagingData<Movie>>
    fun getTopTvShowsCollectionFromServer(): Flow<PagingData<Movie>>
    fun getUpComingCollectionFromServer(): Flow<PagingData<Movie>>
}