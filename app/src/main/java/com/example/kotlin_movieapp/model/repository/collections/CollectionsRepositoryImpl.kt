package com.example.kotlin_movieapp.model.repository.collections

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kotlin_movieapp.model.datasource.domain.collection.Movie
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import com.example.kotlin_movieapp.view.home.top250Movie.Top250PagingSource
import com.example.kotlin_movieapp.view.home.topTvShows.TopTvShowsPagingSource
import com.example.kotlin_movieapp.view.home.upComing.UpComingPagingSource
import kotlinx.coroutines.flow.Flow

class CollectionsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : CollectionsRepository {

    override fun getTop250CollectionFromServer(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { Top250PagingSource(remoteDataSource.getKinopoiskAPI()) }
        ).flow
    }

    override fun getTopTvShowsCollectionFromServer(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { TopTvShowsPagingSource(remoteDataSource.getKinopoiskAPI()) }
        ).flow
    }

    override fun getUpComingCollectionFromServer(): Flow<PagingData<Movie>> {
       return Pager(
           config = PagingConfig(pageSize = 20, enablePlaceholders = false),
           pagingSourceFactory = { UpComingPagingSource(remoteDataSource.getKinopoiskAPI()) }
       ).flow
    }
}