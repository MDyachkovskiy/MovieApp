package com.test.application.core.repository.favorites

import com.test.application.core.domain.movieDetail.MovieDetails
import com.example.kotlin_movieapp.model.datasource.local.room.favorites.FavoriteMovieDao
import com.test.application.core.domain.favorites.FavoriteMovieItem
import com.example.kotlin_movieapp.utils.*
import com.test.application.remote_data.repository.FavoritesRepository

class FavoritesRepositoryImpl (
    private val localDataSource : FavoriteMovieDao
        ) : com.test.application.remote_data.repository.FavoritesRepository {

    override suspend fun getAllFavorites(): List<FavoriteMovieItem> {
        return convertListFavoritesEntityToMovie(localDataSource.all())
    }

    override suspend fun saveEntity(movie: MovieDetails) {
        val favoriteEntity = convertMovieDTOtoFavoriteMovieEntity(movie)
        localDataSource.insert(favoriteEntity)
    }

    override suspend fun deleteEntity(movie: MovieDetails) {
        localDataSource.deleteById(movie.id)
    }

    override suspend fun checkExistenceInDB(id: Int): Boolean {
        return localDataSource.checkExists(id)
    }
}