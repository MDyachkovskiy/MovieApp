package com.test.application.local_data.repository

import com.test.application.core.repository.HistoryRepository
import com.test.application.local_data.history.HistoryDao
import com.test.application.core.domain.history.HistoryMovieItem
import com.test.application.core.domain.movieDetail.MovieDetails
import com.test.application.local_data.mapper.toDomain
import com.test.application.local_data.mapper.toHistoryEntity

class HistoryRepositoryImpl (
    private val localDataSource : HistoryDao
) : HistoryRepository {

    override suspend fun getAllHistory(): List<HistoryMovieItem> {
        return localDataSource.all().map { historyEntity ->
            historyEntity.toDomain()
        }
    }

    override suspend fun saveEntity(movie: MovieDetails, date: Long) {
        val historyEntity = movie.toHistoryEntity()
        historyEntity.date = date
        localDataSource.insert(historyEntity)
    }

    override suspend fun addUserComment(movie: MovieDetails, userComment: String) {
        val historyEntity = localDataSource.getDataByMovieName(movie.name)
        historyEntity.userNote = userComment
        localDataSource.update(historyEntity)
    }
}