package com.example.kotlin_movieapp.utils

import com.example.kotlin_movieapp.model.datasource.domain.movieDetail.MovieDetailsResponse
import com.example.kotlin_movieapp.model.datasource.domain.movieDetail.Poster
import com.example.kotlin_movieapp.model.datasource.domain.collection.CollectionItem
import com.example.kotlin_movieapp.model.datasource.local.room.contacts.ContactsEntity
import com.example.kotlin_movieapp.model.datasource.local.room.contacts.ContactsItem
import com.example.kotlin_movieapp.model.datasource.local.room.favorites.FavoriteMovieEntity
import com.example.kotlin_movieapp.model.datasource.local.room.favorites.FavoriteMovieItem
import com.example.kotlin_movieapp.model.datasource.local.room.history.HistoryEntity
import com.example.kotlin_movieapp.model.datasource.local.room.history.HistoryMovieItem


fun convertListHistoryEntityToMovie (entityList : List<HistoryEntity>) : List<HistoryMovieItem>{
    return entityList.map {
        HistoryMovieItem(
            it.kinopoiskId,
            it.name,
            it.description,
            it.poster,
            it.date,
            it.userNote)
    }
}

fun convertMovieToEntity (movie: HistoryMovieItem) : HistoryEntity {
    return HistoryEntity(
        movie.kinopoiskId ?: 0,
        movie.name,
        movie.description,
        movie.poster,
        movie.date,
        movie.userNote)
}

fun convertHistoryEntityToMovie(historyEntity: HistoryEntity) : HistoryMovieItem =
    HistoryMovieItem (
        kinopoiskId = historyEntity.kinopoiskId,
        name = historyEntity.name,
        description = historyEntity.description,
        poster = historyEntity.poster,
        date = historyEntity.date,
        userNote = historyEntity.userNote
)

fun convertMovieDTOToHistoryMovieItem(movie: MovieDetailsResponse) : HistoryMovieItem =
    HistoryMovieItem (
        kinopoiskId = movie.id,
        name = movie.name,
        description = movie.description,
        poster = movie.poster?.previewUrl,
        date = 0,
        userNote = ""
)

fun convertHistoryMovieItemToCollectionItem (movie: HistoryMovieItem) : CollectionItem =
    CollectionItem(
        kinopoiskId = movie.kinopoiskId,
        name = movie.name,
        description = movie.description,
        poster = Poster(previewUrl = movie.poster),
)


fun convertListFavoritesEntityToMovie (entityList : List<FavoriteMovieEntity>) : List<FavoriteMovieItem>{
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

fun convertMovieDTOtoFavoriteMovieEntity (movie : MovieDetailsResponse) : FavoriteMovieEntity =
    FavoriteMovieEntity(
        kinopoiskId = movie.id,
        name = movie.name,
        description = movie.description,
        poster = movie.poster?.previewUrl,
        date = 0,
        userNote = "",
        isFavorite = true
    )

fun convertFavoriteMovieItemToCollectionItem (movie: FavoriteMovieItem) : CollectionItem =
    CollectionItem (
        kinopoiskId = movie.kinopoiskId,
        name = movie.name,
        description = movie.description,
        poster = Poster(previewUrl = movie.poster),
    )

fun convertListContactsEntityToContactsItem (entityList : List<ContactsEntity>) : List<ContactsItem>
{
    return entityList.map {
        ContactsItem(
            it.id,
            it.name,
            it.phoneNumber)
    }
}

fun convertContactsItemToContactsEntity (contact: ContactsItem):
        ContactsEntity = ContactsEntity (
        id = contact.id,
        name = contact.name,
        phoneNumber = contact.phoneNumber
    )

fun <T> List<T>.convert(getValue: (T) -> String?) = this.joinToString(", "){
    getValue(it) ?: "" }