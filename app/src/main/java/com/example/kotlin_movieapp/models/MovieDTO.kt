package com.example.kotlin_movieapp.models

data class MovieDTO(
    val budget: Budget?,
    val countries: List<Country>?,
    val createDate: String?,
    val description: String?,
    val genres: List<Genre>?,
    val id: Int?,
    val type: String?,
    val movieLength: Int?,
    val name: String?,
    val poster: Poster?,
    val rating: Rating?,
    val similarMovies: List<SimilarMovy>?,
    val year: Int?
)