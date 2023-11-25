package com.test.application.remote_data.repository

import com.test.application.core.domain.movieDetail.MovieDetailsResponse
import com.test.application.core.domain.favorites.FavoriteMovieItem
import com.test.application.remote_data.dto.MovieDetailsResponse

interface FavoritesRepository {
    suspend fun getAllFavorites() : List<FavoriteMovieItem>
    suspend fun saveEntity(movie : MovieDetailsResponse)
    suspend fun deleteEntity(movie : MovieDetailsResponse)

    suspend fun checkExistenceInDB(id: Int): Boolean
}