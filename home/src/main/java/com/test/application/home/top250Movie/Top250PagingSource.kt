package com.test.application.home.top250Movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kotlin_movieapp.model.datasource.domain.collection.Movie
import com.example.kotlin_movieapp.model.datasource.remote.KinopoiskAPI
import com.test.application.core.domain.collection.Movie

class Top250PagingSource(
    private val apiService: KinopoiskAPI
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getTop250CollectionAsync(page = currentPage).await()
            val nextPage = if (currentPage < response.pages) currentPage + 1 else null

            val movies = response.movie
            LoadResult.Page(
                data = movies,
                prevKey = if (currentPage == 1) null else currentPage -1,
                nextKey = nextPage
            )
        } catch (e:Exception) {
            LoadResult.Error(e)
        }
    }
}