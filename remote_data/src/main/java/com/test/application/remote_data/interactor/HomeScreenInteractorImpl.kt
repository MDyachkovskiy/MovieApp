package com.test.application.remote_data.interactor

import androidx.paging.PagingData
import com.test.application.core.domain.collection.Movie
import com.test.application.core.interactor.HomeScreenInteractor
import com.test.application.core.repository.CollectionsRepository
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