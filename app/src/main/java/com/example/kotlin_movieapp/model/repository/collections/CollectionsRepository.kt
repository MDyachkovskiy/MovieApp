package com.example.kotlin_movieapp.model.repository.collections

import androidx.paging.PagingData
import com.example.kotlin_movieapp.model.datasource.domain.collection.Doc
import kotlinx.coroutines.flow.Flow

interface CollectionsRepository {
    fun getTop250CollectionFromServer(): Flow<PagingData<Doc>>
    fun getTopTvShowsCollectionFromServer(): Flow<PagingData<Doc>>
    fun getUpComingCollectionFromServer(): Flow<PagingData<Doc>>
}