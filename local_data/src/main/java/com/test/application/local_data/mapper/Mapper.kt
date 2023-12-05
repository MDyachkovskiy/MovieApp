package com.test.application.local_data.mapper

import com.test.application.core.domain.contacts.ContactsItem
import com.test.application.core.domain.favorites.FavoriteMovieItem
import com.test.application.core.domain.history.HistoryMovieItem
import com.test.application.core.domain.movieDetail.MovieDetails
import com.test.application.local_data.contacts.ContactsEntity
import com.test.application.local_data.favorites.FavoriteMovieEntity
import com.test.application.local_data.history.HistoryEntity

fun convertListFavoritesEntityToMovie (entityList : List<FavoriteMovieEntity>) : List<FavoriteMovieItem>{
    return entityList.map {
        FavoriteMovieItem(
            it.kinopoiskId,
            it.name,
            it.description,
            it.poster,
            it.date,
            it.userNote,
            it.isFavorite
        )
    }
}

fun convertMovieDetailsToFavoriteMovieEntity (movie : MovieDetails) : FavoriteMovieEntity =
    FavoriteMovieEntity(
        kinopoiskId = movie.id,
        name = movie.name,
        description = movie.description,
        poster = movie.poster?.previewUrl,
        date = 0,
        userNote = "",
        isFavorite = true
    )

fun ContactsItem.toEntity() : ContactsEntity {
    return ContactsEntity(
        id = this.id,
        name = this.name,
        phoneNumber = this.phoneNumber
    )
}

fun ContactsEntity.toDomain() : ContactsItem {
    return ContactsItem(
        id = this.id,
        name = this.name,
        phoneNumber = this.phoneNumber
    )
}

fun HistoryEntity.toDomain() : HistoryMovieItem {
    return HistoryMovieItem(
        kinopoiskId = this.kinopoiskId,
        name = this.name,
        description = this.description,
        poster = this.poster,
        date = this.date,
        userNote = this.userNote
    )
}

fun MovieDetails.toHistoryEntity() : HistoryEntity {
    return HistoryEntity(
        kinopoiskId = this.id,
        name = this.name,
        description = this.description,
        poster = this.poster?.previewUrl,
        date = 0,
        userNote = ""
    )
}
