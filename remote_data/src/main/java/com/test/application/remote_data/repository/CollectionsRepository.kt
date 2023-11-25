package com.test.application.remote_data.repository

import androidx.paging.PagingData
import com.test.application.core.domain.collection.Movie
import kotlinx.coroutines.flow.Flow

interface CollectionsRepository {
    fun getTop250CollectionFromServer(): Flow<PagingData<Movie>>
    fun getTopTvShowsCollectionFromServer(): Flow<PagingData<Movie>>
    fun getUpComingCollectionFromServer(): Flow<PagingData<Movie>>
}