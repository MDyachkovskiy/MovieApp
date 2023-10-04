package com.example.kotlin_movieapp.view.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kotlin_movieapp.model.datasource.domain.searchCollection.Doc
import com.example.kotlin_movieapp.model.datasource.remote.KinopoiskAPI

class SearchMoviePagingSource(
    private val apiService: KinopoiskAPI,
    private val query: String
) : PagingSource<Int, Doc>() {
    override fun getRefreshKey(state: PagingState<Int, Doc>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Doc> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getSearchMovieAsync(query = query, page = currentPage).await()
            val nextPage = if (currentPage < response.pages) currentPage + 1 else null

            val movies = response.docs
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