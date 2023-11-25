package com.test.application.remote_data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface CollectionsRepository<T: Any> {
    fun getTop250CollectionFromServer(): Flow<PagingData<T>>
    fun getTopTvShowsCollectionFromServer(): Flow<PagingData<T>>
    fun getUpComingCollectionFromServer(): Flow<PagingData<T>>
}