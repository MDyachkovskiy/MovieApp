package com.test.application.core.repository.collections

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.test.application.core.domain.collection.Movie
import com.test.application.home.top250Movie.Top250PagingSource
import com.test.application.remote_data.api.KinopoiskService
import com.test.application.remote_data.repository.CollectionsRepository
import kotlinx.coroutines.flow.Flow

class CollectionsRepositoryImpl(
    private val kinopoiskService: KinopoiskService
) : CollectionsRepository<Movie> {

    override fun getTop250CollectionFromServer(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { Top250PagingSource(kinopoiskService) }
        ).flow
    }

    override fun getTopTvShowsCollectionFromServer(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { TopTvShowsPagingSource(kinopoiskService) }
        ).flow
    }

    override fun getUpComingCollectionFromServer(): Flow<PagingData<Movie>> {
       return Pager(
           config = PagingConfig(pageSize = 20, enablePlaceholders = false),
           pagingSourceFactory = { UpComingPagingSource(kinopoiskService) }
       ).flow
    }
}