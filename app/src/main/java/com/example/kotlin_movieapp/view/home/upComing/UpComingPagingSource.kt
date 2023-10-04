package com.example.kotlin_movieapp.view.home.upComing

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kotlin_movieapp.model.datasource.domain.collection.Doc
import com.example.kotlin_movieapp.model.datasource.remote.KinopoiskAPI

class UpComingPagingSource(
    private val apiService: KinopoiskAPI
) : PagingSource<Int, Doc>() {

    override fun getRefreshKey(state: PagingState<Int, Doc>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Doc> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getUpComingCollectionAsync(page = currentPage).await()
            val nextPage = if (currentPage < response.pages) currentPage + 1 else null

            val newMovies = response.docs
            LoadResult.Page(
                data = newMovies,
                prevKey = if(currentPage == 1) null else currentPage -1,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}