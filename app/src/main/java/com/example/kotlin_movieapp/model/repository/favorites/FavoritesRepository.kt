package com.example.kotlin_movieapp.model.repository.favorites

import com.example.kotlin_movieapp.model.datasource.domain.movieDetail.MovieDetailsResponse
import com.example.kotlin_movieapp.model.datasource.local.room.favorites.FavoriteMovieItem

interface FavoritesRepository {
    fun getAllFavorites() : List<FavoriteMovieItem>
    fun saveEntity(movie : MovieDetailsResponse)
    fun deleteEntity(movie : MovieDetailsResponse)
}