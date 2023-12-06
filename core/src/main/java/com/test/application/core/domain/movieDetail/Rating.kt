package com.test.application.core.domain.movieDetail

data class Rating(
    val await: String? = "",
    val filmCritics: Double? = 0.0,
    val imdb: Double? = 0.0,
    val kp: Double? = 0.0,
    val russianFilmCritics: Double? = 0.0)