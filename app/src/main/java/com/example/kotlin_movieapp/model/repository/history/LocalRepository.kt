package com.example.kotlin_movieapp.model.repository.history

import android.text.Editable
import com.example.kotlin_movieapp.model.datasource.domain.movieDetail.MovieDetailsResponse
import com.example.kotlin_movieapp.model.datasource.local.room.history.HistoryMovieItem

interface LocalRepository {
    fun getAllHistory() : List<HistoryMovieItem>
    fun saveEntity(movie : MovieDetailsResponse, date: Long)
    fun addUserComment(movie : MovieDetailsResponse, text: Editable?)
}