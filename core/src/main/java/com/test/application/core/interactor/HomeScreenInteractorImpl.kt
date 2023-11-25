package com.test.application.core.interactor

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.test.application.core.domain.collection.Movie
import com.test.application.home.top250Movie.Top250PagingSource
import com.test.application.remote_data.repository.CollectionsRepository
import kotlinx.coroutines.flow.Flow

class HomeScreenInteractorImpl(
    private val remoteDataSource: CollectionsRepository<Movie>
) : HomeScreenInteractor {
    override fun getTop250CollectionFromServer(): Flow<PagingData<Movie>> {
       return remoteDataSource.getTop250CollectionFromServer()
    }

    override fun getTopTvShowsCollectionFromServer(): Flow<PagingData<Movie>> {
        return remoteDataSource.getTop250CollectionFromServer()
    }

    override fun getUpComingCollectionFromServer(): Flow<PagingData<Movie>> {
        return remoteDataSource.getUpComingCollectionFromServer()
    }
}