package com.example.kotlin_movieapp.model.repository.favorites

import com.example.kotlin_movieapp.model.datasource.remote.movieDetailsResponse.MovieDTO
import com.example.kotlin_movieapp.model.datasource.local.room.favorites.FavoriteMovieItem

interface FavoritesRepository {
    fun getAllFavorites() : List<FavoriteMovieItem>
    fun saveEntity(movie : MovieDTO)
    fun deleteEntity(movie : MovieDTO)
}