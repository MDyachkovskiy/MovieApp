package com.test.application.remote_data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.test.application.core.domain.searchCollection.SearchMovie
import com.test.application.remote_data.api.KinopoiskService
import com.test.application.remote_data.mapper.toDomain

class SearchMoviePagingSource(
    private val kinopoiskService: KinopoiskService,
    private val query: String
) : PagingSource<Int, SearchMovie>() {
    override fun getRefreshKey(state: PagingState<Int, SearchMovie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchMovie> {
        return try {
            val currentPage = params.key ?: 1
            val responseDTO = kinopoiskService.getSearchMovieAsync(query = query, page = currentPage).await()
            val response = responseDTO.toDomain()
            val nextPage = if (currentPage < response.pages) currentPage + 1 else null
            val movies = response.movies
            LoadResult.Page(
                data = movies,
                prevKey = if (currentPage == 1) null else currentPage -1,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}