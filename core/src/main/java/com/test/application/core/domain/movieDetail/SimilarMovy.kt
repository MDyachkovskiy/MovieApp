package com.test.application.core.domain.movieDetail

data class SimilarMovy(
    val alternativeName: String? = "",
    val enName: String? = "",
    val id: Int = 0,
    val name: String? = "",
    val movieDetailsPoster: MovieDetailsPoster = MovieDetailsPoster(),
    val type: String? = ""
)