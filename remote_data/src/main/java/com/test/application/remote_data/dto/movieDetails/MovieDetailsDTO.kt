package com.test.application.remote_data.dto.movieDetails

data class MovieDetailsDTO(
    val budget: BudgetDTO? = BudgetDTO(),
    val countries: List<CountryDTO>? = listOf(),
    val description: String? = "",
    val slogan: String? = "",
    val genres: List<GenreDTO>? = listOf(),
    val id: Int = 0,
    val movieLength: Int? = 0,
    val name: String = "",
    val persons: List<PersonDTO> = listOf(),
    val poster: MovieDetailsPosterDTO? = MovieDetailsPosterDTO(),
    val backdrop: BackdropDTO? = BackdropDTO(),
    val facts: List<FactsDTO>? = listOf(),
    val premiere: PremiereDTO? = PremiereDTO(),
    val rating: RatingDTO? = RatingDTO(),
    val votes: VotesDTO? = VotesDTO(),
    val similarMovies: List<SimilarMovyDTO>? = listOf(),
    val videos: VideosDTO = VideosDTO(),
    val type: String? = "",
    val year: Int? = 0
)