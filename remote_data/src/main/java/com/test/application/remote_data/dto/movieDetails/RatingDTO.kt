package com.test.application.remote_data.dto.movieDetails

data class RatingDTO(
    val await: String = "",
    val filmCritics: Double = 0.0,
    val imdb: Double = 0.0,
    val kp: Double = 0.0,
    val russianFilmCritics: Double = 0.0
)