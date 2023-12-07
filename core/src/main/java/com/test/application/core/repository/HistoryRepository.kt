package com.test.application.core.repository

import com.test.application.core.domain.history.HistoryMovieItem
import com.test.application.core.domain.movieDetail.MovieDetails

interface HistoryRepository {
    suspend fun getAllHistory() : List<HistoryMovieItem>
    suspend fun saveEntity(movie : MovieDetails, date: Long)
    suspend fun addUserComment(movie : MovieDetails, userComment: String)
}