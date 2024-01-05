package com.test.application.remote_data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.test.application.core.domain.collection.Movie
import com.test.application.remote_data.api.KinopoiskService
import com.test.application.remote_data.mapper.toDomain
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class UpComingPagingSource(
    private val kinopoiskService: KinopoiskService,
    private val type: String
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val dateRange = getDateRange()
            val responseDTO = kinopoiskService.getUpComingCollectionAsync(
                page = currentPage,
                type = type,
                premiereWorld = dateRange
            ).await()


            val filteredMoviesDTO = responseDTO.movie.filter{
                it.name != null && it.poster?.url != null
            }

            val filteredMovies = filteredMoviesDTO.map { it.toDomain() }

            val nextPage = if (currentPage < responseDTO.pages) currentPage + 1 else null

            LoadResult.Page(
                data = filteredMovies,
                prevKey = if(currentPage == 1) null else currentPage -1,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun getDateRange(): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val calendar = Calendar.getInstance()

        calendar.add(Calendar.DAY_OF_YEAR, -15)
        val startDate = dateFormat.format(calendar.time)

        calendar.add(Calendar.DAY_OF_YEAR, 30)
        val endDate = dateFormat.format(calendar.time)

        return "$startDate-$endDate"
    }
}