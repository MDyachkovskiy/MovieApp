package com.example.kotlin_movieapp.view.home.top250Movie

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kotlin_movieapp.model.datasource.domain.collection.Doc
import com.example.kotlin_movieapp.model.datasource.remote.KinopoiskAPI

class Top250PagingSource(
    private val apiService: KinopoiskAPI
) : PagingSource<Int, Doc>() {

    override fun getRefreshKey(state: PagingState<Int, Doc>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Doc> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getTop250CollectionAsync(page = currentPage).await()
            Log.d("@@@", "Loading page: $currentPage, received ${response.docs.size} items")
            val nextPage = if (currentPage < response.pages) currentPage + 1 else null

            val movies = response.docs
            LoadResult.Page(
                data = movies,
                prevKey = if (currentPage == 1) null else currentPage -1,
                nextKey = nextPage
            )
        } catch (e:Exception) {
            Log.d("@@@", "Error loading data: ${e.message}")
            LoadResult.Error(e)
        }
    }
}