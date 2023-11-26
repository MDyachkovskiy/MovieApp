package com.example.kotlin_movieapp.model.repository.history

import android.text.Editable
import com.test.application.core.domain.movieDetail.MovieDetails
import com.test.application.local_data.history.HistoryMovieItem

interface LocalRepository {
    suspend fun getAllHistory() : List<com.test.application.local_data.history.HistoryMovieItem>
    suspend fun saveEntity(movie : MovieDetails, date: Long)
    suspend fun addUserComment(movie : MovieDetails, text: Editable?)
}