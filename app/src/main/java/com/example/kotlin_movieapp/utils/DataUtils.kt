package com.example.kotlin_movieapp.utils

import com.example.kotlin_movieapp.model.collectionResponse.CollectionItem
import com.example.kotlin_movieapp.model.movieDetailsResponse.MovieDTO
import com.example.kotlin_movieapp.model.movieDetailsResponse.Poster
import com.example.kotlin_movieapp.model.room.HistoryEntity
import com.example.kotlin_movieapp.model.room.HistoryMovieItem


fun convertListHistoryEntitytoMovie (entityList : List<HistoryEntity>) : List<HistoryMovieItem>{
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
        movie.kinopoiskId,
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

fun convertMovieDTOToHistoryMovieItem(movie: MovieDTO) : HistoryMovieItem =
    HistoryMovieItem (
        kinopoiskId = movie.id,
        name = movie.name,
        description = movie.description,
        poster = movie.poster?.previewUrl,
        date = 0,
        userNote = ""
)

fun convertHistoryMovieItemToCollectionItem (movie: HistoryMovieItem) : CollectionItem =
    CollectionItem (
        kinopoiskId = movie.kinopoiskId,
        name = movie.name,
        description = movie.description,
        poster = Poster(previewUrl = movie.poster),
)

