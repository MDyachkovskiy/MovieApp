package com.test.application.remote_data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.test.application.core.domain.searchCollection.SearchMovie
import com.test.application.core.repository.SearchRepository
import com.test.application.remote_data.api.KinopoiskService
import com.test.application.remote_data.paging_source.SearchMoviePagingSource
import kotlinx.coroutines.flow.Flow


class SearchRepositoryImpl (
    private val kinopoiskService: KinopoiskService
) : SearchRepository {

    override fun getSearchCollection(name: String): Flow<PagingData<SearchMovie>> {
        Log.d("@@@", "getSearchCollection called with query: $name")
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { SearchMoviePagingSource(kinopoiskService, name) }
        ).flow
    }
}