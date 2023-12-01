package com.test.application.local_data.repository

import android.text.Editable
import com.test.application.core.domain.movieDetail.MovieDetails
import com.test.application.core.repository.HistoryRepository

class HistoryRepositoryImpl (
    private val localDataSource : com.test.application.local_data.history.HistoryDao
        ) : HistoryRepository {

    override suspend fun getAllHistory(): List<com.test.application.local_data.history.HistoryMovieItem> {
        return convertListHistoryEntityToMovie(localDataSource.all())
    }

    override suspend fun saveEntity(movie: MovieDetails, date: Long) {
        val historyEntity = convertMovieDTOToHistoryMovieItem(movie)
        historyEntity.date = date
        localDataSource.insert(convertMovieToEntity(historyEntity))
    }

    override suspend fun addUserComment(movie: MovieDetails, text: Editable?) {
        val dbMovieEntity = convertHistoryEntityToMovie(localDataSource.getDataByMovieName(movie.name))
        dbMovieEntity.userNote = text.toString()
        localDataSource.update(convertMovieToEntity(dbMovieEntity))
    }
}