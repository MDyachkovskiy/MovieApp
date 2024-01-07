package com.test.application.core.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface CollectionsRepository<T: Any> {
    fun getTop250CollectionFromServer(type: String): Flow<PagingData<T>>
    fun getTopTvShowsCollectionFromServer(type: String): Flow<PagingData<T>>
    fun getUpComingCollectionFromServer(type: String): Flow<PagingData<T>>
}