package com.example.kotlin_movieapp.model.repository.history

import android.text.Editable
import com.example.kotlin_movieapp.model.datasource.remote.movieDetailsResponse.MovieDTO
import com.example.kotlin_movieapp.model.datasource.local.room.history.HistoryMovieItem

interface LocalRepository {
    fun getAllHistory() : List<HistoryMovieItem>
    fun saveEntity(movie : MovieDTO, date: Long)
    fun addUserComment(movie : MovieDTO, text: Editable?)
}