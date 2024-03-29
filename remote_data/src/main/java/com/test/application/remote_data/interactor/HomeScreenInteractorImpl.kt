package com.test.application.remote_data.interactor

import androidx.paging.PagingData
import com.test.application.core.domain.collection.Movie
import com.test.application.core.interactor.HomeScreenInteractor
import com.test.application.core.repository.CollectionsRepository
import kotlinx.coroutines.flow.Flow

class HomeScreenInteractorImpl(
    private val remoteDataSource: CollectionsRepository<Movie>
) : HomeScreenInteractor {
    override fun getTop250CollectionFromServer(type: String): Flow<PagingData<Movie>> {
       return remoteDataSource.getTop250CollectionFromServer(type)
    }

    override fun getTopTvShowsCollectionFromServer(type: String): Flow<PagingData<Movie>> {
        return remoteDataSource.getTopTvShowsCollectionFromServer(type)
    }

    override fun getUpComingCollectionFromServer(type: String): Flow<PagingData<Movie>> {
        return remoteDataSource.getUpComingCollectionFromServer(type)
    }
}