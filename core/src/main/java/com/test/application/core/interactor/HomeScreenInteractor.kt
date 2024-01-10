package com.test.application.core.interactor

import androidx.paging.PagingData
import com.test.application.core.domain.collection.Movie
import kotlinx.coroutines.flow.Flow

interface HomeScreenInteractor {
    fun getTop250CollectionFromServer(type: String): Flow<PagingData<Movie>>
    fun getTopTvShowsCollectionFromServer(type: String): Flow<PagingData<Movie>>
    fun getUpComingCollectionFromServer(type: String): Flow<PagingData<Movie>>
}