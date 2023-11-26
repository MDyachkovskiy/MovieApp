package com.test.application.local_data.repository

import com.test.application.core.domain.favorites.FavoriteMovieItem
import com.test.application.core.domain.movieDetail.MovieDetails
import com.test.application.core.repository.favorites.FavoritesRepository
import com.test.application.local_data.favorites.FavoriteMovieDao
import com.test.application.local_data.mapper.convertListFavoritesEntityToMovie
import com.test.application.local_data.mapper.convertMovieDetailsToFavoriteMovieEntity

class FavoritesRepositoryImpl (
    private val localDataSource : FavoriteMovieDao
) : FavoritesRepository {

    override suspend fun getAllFavorites(): List<FavoriteMovieItem> {
        return convertListFavoritesEntityToMovie(localDataSource.all())
    }

    override suspend fun saveEntity(movie: MovieDetails) {
        val favoriteEntity = convertMovieDetailsToFavoriteMovieEntity(movie)
        localDataSource.insert(favoriteEntity)
    }

    override suspend fun deleteEntity(movie: MovieDetails) {
        localDataSource.deleteById(movie.id)
    }

    override suspend fun checkExistenceInDB(id: Int): Boolean {
        return localDataSource.checkExists(id)
    }
}