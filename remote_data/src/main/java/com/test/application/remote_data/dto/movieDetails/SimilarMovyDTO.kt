package com.test.application.remote_data.dto.movieDetails

data class SimilarMovyDTO(
    val alternativeName: String = "",
    val enName: String = "",
    val id: Int = 0,
    val name: String = "",
    val poster: MovieDetailsPosterDTO = MovieDetailsPosterDTO(),
    val type: String = "",
    val year: String = "",
    val rating: RatingDTO = RatingDTO()
)