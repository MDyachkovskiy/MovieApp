package com.test.application.local_data.mapper

import com.test.application.core.domain.favorites.FavoriteMovieItem
import com.test.application.core.domain.movieDetail.MovieDetails

fun convertListFavoritesEntityToMovie (entityList : List<com.test.application.local_data.favorites.FavoriteMovieEntity>) : List<FavoriteMovieItem>{
    return entityList.map {
        FavoriteMovieItem(
            it.kinopoiskId,
            it.name,
            it.description,
            it.poster,
            it.date,
            it.userNote,
            it.isFavorite)
    }
}

fun convertMovieDetailsToFavoriteMovieEntity (movie : MovieDetails) : com.test.application.local_data.favorites.FavoriteMovieEntity =
    com.test.application.local_data.favorites.FavoriteMovieEntity(
        kinopoiskId = movie.id,
        name = movie.name,
        description = movie.description,
        poster = movie.poster?.previewUrl,
        date = 0,
        userNote = "",
        isFavorite = true
    )