package com.example.kotlin_movieapp.view.home.topTvShows

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kotlin_movieapp.model.datasource.domain.collection.Movie
import com.example.kotlin_movieapp.model.datasource.remote.KinopoiskAPI

class TopTvShowsPagingSource(
    private val apiService: KinopoiskAPI
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getTopTvShowsCollectionAsync(page = currentPage).await()
            val nextPage = if(currentPage < response.pages) currentPage + 1 else null

            val tvShows = response.movie
            LoadResult.Page(
                data = tvShows,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}