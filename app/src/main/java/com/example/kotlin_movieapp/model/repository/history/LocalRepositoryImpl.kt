package com.example.kotlin_movieapp.model.repository.history

import android.text.Editable
import com.example.kotlin_movieapp.model.datasource.domain.movieDetail.MovieDetailsResponse
import com.example.kotlin_movieapp.model.datasource.local.room.history.HistoryDao
import com.example.kotlin_movieapp.model.datasource.local.room.history.HistoryMovieItem
import com.example.kotlin_movieapp.utils.*

class LocalRepositoryImpl (
    private val localDataSource : HistoryDao
        ) : LocalRepository {

    override fun getAllHistory(): List<HistoryMovieItem> {
        return convertListHistoryEntityToMovie(localDataSource.all())
    }

    override fun saveEntity(movie: MovieDetailsResponse, date: Long) {
        val historyEntity = convertMovieDTOToHistoryMovieItem(movie)
        historyEntity.date = date
        localDataSource.insert(convertMovieToEntity(historyEntity))
    }

    override fun addUserComment(movie: MovieDetailsResponse, text: Editable?) {
        val dbMovieEntity = convertHistoryEntityToMovie(localDataSource.getDataByMovieName(movie.name))
        dbMovieEntity.userNote = text.toString()
        localDataSource.update(convertMovieToEntity(dbMovieEntity))
    }
}