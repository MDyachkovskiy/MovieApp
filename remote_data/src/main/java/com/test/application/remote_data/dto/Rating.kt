package com.test.application.remote_data.dto

data class Rating(
    val await: Any = Any(),
    val filmCritics: Double = 0.0,
    val imdb: Double = 0.0,
    val kp: Double = 0.0,
    val russianFilmCritics: Int = 0
)