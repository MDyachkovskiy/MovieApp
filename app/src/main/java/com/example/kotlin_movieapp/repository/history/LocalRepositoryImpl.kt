package com.example.kotlin_movieapp.repository.history

import com.example.kotlin_movieapp.model.movieDetailsResponse.MovieDTO
import com.example.kotlin_movieapp.model.room.HistoryDao

class LocalRepositoryImpl (
    private val localDataSource : HistoryDao
        ) : LocalRepository {
    override fun getAllHistory(): List<MovieDTO> {
        TODO("Not yet implemented")
    }

    override fun saveEntity(movie: MovieDTO) {
        TODO("Not yet implemented")
    }
}