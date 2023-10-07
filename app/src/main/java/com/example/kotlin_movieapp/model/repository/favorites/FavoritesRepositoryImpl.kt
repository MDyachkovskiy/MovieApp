package com.example.kotlin_movieapp.model.repository.favorites

import com.example.kotlin_movieapp.model.datasource.domain.movieDetail.MovieDetailsResponse
import com.example.kotlin_movieapp.model.datasource.local.room.favorites.FavoriteMovieDao
import com.example.kotlin_movieapp.model.datasource.local.room.favorites.FavoriteMovieItem
import com.example.kotlin_movieapp.utils.*

class FavoritesRepositoryImpl (
    private val localDataSource : FavoriteMovieDao
        ) : FavoritesRepository {

    override suspend fun getAllFavorites(): List<FavoriteMovieItem> {
        return convertListFavoritesEntityToMovie(localDataSource.all())
    }

    override suspend fun saveEntity(movie: MovieDetailsResponse) {
        val favoriteEntity = convertMovieDTOtoFavoriteMovieEntity(movie)
        localDataSource.insert(favoriteEntity)
    }

    override suspend fun deleteEntity(movie: MovieDetailsResponse) {
        localDataSource.deleteById(movie.id)
    }

    override suspend fun checkExistenceInDB(id: Int): Boolean {
        return localDataSource.checkExists(id)
    }
}