package com.example.kotlin_movieapp.model.repository.favorites

import com.example.kotlin_movieapp.model.datasource.remote.movieDetailsResponse.MovieDTO
import com.example.kotlin_movieapp.model.datasource.local.room.favorites.FavoriteMovieDao
import com.example.kotlin_movieapp.model.datasource.local.room.favorites.FavoriteMovieItem
import com.example.kotlin_movieapp.utils.*

class FavoritesRepositoryImpl (
    private val localDataSource : FavoriteMovieDao
        ) : FavoritesRepository {

    override fun getAllFavorites(): List<FavoriteMovieItem> {
        return convertListFavoritesEntityToMovie(localDataSource.all())
    }

    override fun saveEntity(movie: MovieDTO) {
        val favoriteEntity = convertMovieDTOtoFavoriteMovieEntity(movie)
        localDataSource.insert(favoriteEntity)
    }

    override fun deleteEntity(movie: MovieDTO) {
        localDataSource.deleteById(movie.id)
    }
}