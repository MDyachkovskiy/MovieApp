package com.test.application.remote_data.dto.collection

import com.test.application.remote_data.dto.movieDetails.RatingDTO

data class MovieDTO(
    val id: Int = 0,
    val rating: RatingDTO = RatingDTO(),
    val name: String = "",
    val poster: PosterDTO = PosterDTO(),
    val description: String? = ""
)