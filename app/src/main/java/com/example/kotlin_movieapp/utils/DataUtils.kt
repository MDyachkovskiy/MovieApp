package com.example.kotlin_movieapp.utils

import com.test.application.core.domain.movieDetail.MovieDetails
import com.test.application.core.domain.collection.Movie
import com.test.application.core.domain.collection.Poster
import com.test.application.local_data.contacts.ContactsEntity
import com.test.application.local_data.contacts.ContactsItem
import com.test.application.local_data.favorites.FavoriteMovieEntity
import com.test.application.core.domain.favorites.FavoriteMovieItem
import com.test.application.local_data.history.HistoryEntity
import com.test.application.local_data.history.HistoryMovieItem


fun convertListHistoryEntityToMovie (entityList : List<com.test.application.local_data.history.HistoryEntity>) : List<com.test.application.local_data.history.HistoryMovieItem>{
    return entityList.map {
        com.test.application.local_data.history.HistoryMovieItem(
            it.kinopoiskId,
            it.name,
            it.description,
            it.poster,
            it.date,
            it.userNote
        )
    }
}

fun convertMovieToEntity (movie: com.test.application.local_data.history.HistoryMovieItem) : com.test.application.local_data.history.HistoryEntity {
    return com.test.application.local_data.history.HistoryEntity(
        movie.kinopoiskId,
        movie.name,
        movie.description,
        movie.poster,
        movie.date,
        movie.userNote
    )
}

fun convertHistoryEntityToMovie(historyEntity: com.test.application.local_data.history.HistoryEntity) : com.test.application.local_data.history.HistoryMovieItem =
    com.test.application.local_data.history.HistoryMovieItem(
        kinopoiskId = historyEntity.kinopoiskId,
        name = historyEntity.name,
        description = historyEntity.description,
        poster = historyEntity.poster,
        date = historyEntity.date,
        userNote = historyEntity.userNote
    )

fun convertMovieDTOToHistoryMovieItem(movie: MovieDetails) : com.test.application.local_data.history.HistoryMovieItem =
    com.test.application.local_data.history.HistoryMovieItem(
        kinopoiskId = movie.id,
        name = movie.name,
        description = movie.description,
        poster = movie.poster?.previewUrl,
        date = 0,
        userNote = ""
    )

fun convertHistoryMovieItemToMovie (movie: com.test.application.local_data.history.HistoryMovieItem) : Movie =
    Movie(
        id = movie.kinopoiskId,
        name = movie.name ?: "",
        description = movie.description,
        poster = Poster(previewUrl = movie.poster ?: ""),
    )

fun convertFavoriteMovieItemToMovie (movie: FavoriteMovieItem) : Movie =
    Movie (
        id = movie.kinopoiskId,
        name = movie.name ?: "",
        description = movie.description,
        poster = Poster(previewUrl = movie.poster ?: ""),
    )

fun convertListContactsEntityToContactsItem (entityList : List<com.test.application.local_data.contacts.ContactsEntity>) : List<com.test.application.local_data.contacts.ContactsItem>
{
    return entityList.map {
        com.test.application.local_data.contacts.ContactsItem(
            it.id,
            it.name,
            it.phoneNumber
        )
    }
}

fun convertContactsItemToContactsEntity (contact: com.test.application.local_data.contacts.ContactsItem):
        com.test.application.local_data.contacts.ContactsEntity =
    com.test.application.local_data.contacts.ContactsEntity(
        id = contact.id,
        name = contact.name,
        phoneNumber = contact.phoneNumber
    )

fun <T> List<T>.convert(getValue: (T) -> String?) = this.joinToString(", "){
    getValue(it) ?: "" }