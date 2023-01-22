package com.example.kotlin_movieapp.utils

import com.example.kotlin_movieapp.model.movieDetailsResponse.MovieDTO
import com.example.kotlin_movieapp.model.room.HistoryEntity

fun convertHistoryEntitytoMovie (entityList : List<HistoryEntity>) : List<MovieDTO>{


}

fun convertMovieToEntity (movie: MovieDTO) : HistoryEntity {
    return HistoryEntity(0, movie.name, movie.description, 0,"")
}