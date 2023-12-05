package com.test.application.core.domain.movieDetail

data class MovieDetails(
    val budget: Budget? = Budget(),
    val countries: List<Country>? = listOf(),
    val description: String? = "",
    val genres: List<Genre>? = listOf(),
    val id: Int = 0,
    val movieLength: Int? = 0,
    val name: String = "",
    val persons: List<Person> = listOf(),
    val movieDetailsPoster: MovieDetailsPoster? = MovieDetailsPoster(),
    val rating: Rating? = Rating(),
    val similarMovies: List<SimilarMovy>? = listOf(),
    val type: String? = "",
    val year: Int? = 0
)