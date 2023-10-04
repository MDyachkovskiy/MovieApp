package com.example.kotlin_movieapp.model.datasource.domain.movieDetail

data class MovieDetailsResponse(
    val budget: Budget? = Budget(),
    val countries: List<Country>? = listOf(),
    val description: String? = "",
    val genres: List<Genre>? = listOf(),
    val id: Int = 0,
    val movieLength: Int? = 0,
    val name: String = "",
    val persons: List<Person> = listOf(),
    val poster: Poster? = Poster(),
    val rating: Rating? = Rating(),
    val similarMovies: List<SimilarMovy>? = listOf(),
    val type: String? = "",
    val year: Int? = 0
)