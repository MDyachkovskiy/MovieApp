package com.test.application.core.repository

import com.test.application.core.domain.favorites.FavoriteMovieItem
import com.test.application.core.domain.movieDetail.MovieDetails

interface FavoritesRepository {
    suspend fun getAllFavorites(): List<FavoriteMovieItem>
    suspend fun saveEntity(movie: MovieDetails, date: Long)
    suspend fun addUserComment(movie : MovieDetails, userComment: String)
    suspend fun deleteEntity(movieId: Int)
    suspend fun checkExistenceInDB(id: Int): Boolean
}