package com.test.application.remote_data.dto.collection

import com.test.application.remote_data.dto.movieDetails.GenreDTO
import com.test.application.remote_data.dto.movieDetails.RatingDTO

data class MovieDTO(
    val id: Int = 0,
    val rating: RatingDTO = RatingDTO(),
    val name: String? = null,
    val year: String = "",
    val genres: List<GenreDTO>? = listOf(),
    val poster: PosterDTO? = null,
    val description: String? = null
)