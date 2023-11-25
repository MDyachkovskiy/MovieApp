package com.example.kotlin_movieapp.model.repository.history

import android.text.Editable
import com.test.application.core.domain.movieDetail.MovieDetailsResponse
import com.example.kotlin_movieapp.model.datasource.local.room.history.HistoryMovieItem

interface LocalRepository {
    suspend fun getAllHistory() : List<HistoryMovieItem>
    suspend fun saveEntity(movie : MovieDetailsResponse, date: Long)
    suspend fun addUserComment(movie : MovieDetailsResponse, text: Editable?)
}