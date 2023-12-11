package com.test.application.remote_data.dto.movieDetails

data class MovieDetailsDTO(
    val budget: BudgetDTO? = BudgetDTO(),
    val countries: List<CountryDTO>? = listOf(),
    val description: String? = "",
    val genres: List<GenreDTO>? = listOf(),
    val id: Int = 0,
    val movieLength: Int? = 0,
    val name: String = "",
    val persons: List<PersonDTO> = listOf(),
    val poster: MovieDetailsPosterDTO? = MovieDetailsPosterDTO(),
    val rating: RatingDTO? = RatingDTO(),
    val similarMovies: List<SimilarMovyDTO>? = listOf(),
    val type: String? = "",
    val year: Int? = 0
)