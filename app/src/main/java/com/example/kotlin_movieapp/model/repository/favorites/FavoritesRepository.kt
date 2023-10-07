package com.example.kotlin_movieapp.model.repository.favorites

import com.example.kotlin_movieapp.model.datasource.domain.movieDetail.MovieDetailsResponse
import com.example.kotlin_movieapp.model.datasource.local.room.favorites.FavoriteMovieItem

interface FavoritesRepository {
    suspend fun getAllFavorites() : List<FavoriteMovieItem>
    suspend fun saveEntity(movie : MovieDetailsResponse)
    suspend fun deleteEntity(movie : MovieDetailsResponse)

    suspend fun checkExistenceInDB(id: Int): Boolean
}