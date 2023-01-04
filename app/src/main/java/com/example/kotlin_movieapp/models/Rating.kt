package com.example.kotlin_movieapp.models

data class Rating(
    val _id: String,
    val await: Int,
    val filmCritics: Double,
    val imdb: Double,
    val kp: Double,
    val russianFilmCritics: Int
)