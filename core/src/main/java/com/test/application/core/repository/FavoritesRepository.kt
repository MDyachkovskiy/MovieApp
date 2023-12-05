package com.test.application.core.repository

import com.test.application.core.domain.favorites.FavoriteMovieItem
import com.test.application.core.domain.movieDetail.MovieDetails

interface FavoritesRepository {
    suspend fun getAllFavorites(): List<FavoriteMovieItem>
    suspend fun saveEntity(movie: MovieDetails)
    suspend fun deleteEntity(movie: MovieDetails)
    suspend fun checkExistenceInDB(id: Int): Boolean
}