package com.test.application.core.domain.collection

import com.test.application.core.domain.movieDetail.Genre
import com.test.application.core.domain.movieDetail.Rating

data class Movie(
    val id: Int = 0,
    val rating: Rating = Rating(),
    val year: String = "",
    val genres: List<Genre>? = listOf(),
    val name: String = "",
    val poster: Poster = Poster(),
    val description: String? = ""
)