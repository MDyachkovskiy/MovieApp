package com.test.application.core.repository.favorites

import com.test.application.core.domain.movieDetail.MovieDetailsResponse
import com.test.application.core.domain.favorites.FavoriteMovieItem

interface FavoritesRepository {
    suspend fun getAllFavorites() : List<FavoriteMovieItem>
    suspend fun saveEntity(movie : MovieDetailsResponse)
    suspend fun deleteEntity(movie : MovieDetailsResponse)

    suspend fun checkExistenceInDB(id: Int): Boolean
}