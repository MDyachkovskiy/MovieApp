package com.example.kotlin_movieapp.model.repository.history

import android.text.Editable
import com.test.application.core.domain.movieDetail.MovieDetails
import com.example.kotlin_movieapp.model.datasource.local.room.history.HistoryMovieItem

interface LocalRepository {
    suspend fun getAllHistory() : List<HistoryMovieItem>
    suspend fun saveEntity(movie : MovieDetails, date: Long)
    suspend fun addUserComment(movie : MovieDetails, text: Editable?)
}