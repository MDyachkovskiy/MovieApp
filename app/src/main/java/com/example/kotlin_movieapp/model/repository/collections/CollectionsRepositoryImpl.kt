package com.example.kotlin_movieapp.model.repository.collections

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.test.application.core.domain.collection.Movie
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import com.test.application.home.top250Movie.Top250PagingSource
import com.test.application.home.topTvShows.TopTvShowsPagingSource
import com.test.application.home.upComing.UpComingPagingSource
import kotlinx.coroutines.flow.Flow

class CollectionsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : CollectionsRepository {

    override fun getTop250CollectionFromServer(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                com.test.application.home.top250Movie.Top250PagingSource(
                    remoteDataSource.getKinopoiskAPI()
                )
            }
        ).flow
    }

    override fun getTopTvShowsCollectionFromServer(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                com.test.application.home.topTvShows.TopTvShowsPagingSource(
                    remoteDataSource.getKinopoiskAPI()
                )
            }
        ).flow
    }

    override fun getUpComingCollectionFromServer(): Flow<PagingData<Movie>> {
       return Pager(
           config = PagingConfig(pageSize = 20, enablePlaceholders = false),
           pagingSourceFactory = {
               com.test.application.home.upComing.UpComingPagingSource(
                   remoteDataSource.getKinopoiskAPI()
               )
           }
       ).flow
    }
}