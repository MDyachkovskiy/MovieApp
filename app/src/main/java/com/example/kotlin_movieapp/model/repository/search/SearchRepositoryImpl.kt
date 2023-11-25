package com.example.kotlin_movieapp.model.repository.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.test.application.core.domain.searchCollection.Movie
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import com.test.application.search.SearchMoviePagingSource
import kotlinx.coroutines.flow.Flow


class SearchRepositoryImpl (
    private val remoteDataSource: RemoteDataSource
) : SearchRepository {

    override fun getSearchCollection(name: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { SearchMoviePagingSource(remoteDataSource.getKinopoiskAPI(),
            name)
            }
        ).flow
    }
}