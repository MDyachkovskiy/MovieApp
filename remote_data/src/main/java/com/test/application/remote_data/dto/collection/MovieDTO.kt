package com.test.application.remote_data.dto.collection

data class MovieDTO(
    val id: Int = 0,
    val name: String = "",
    val poster: PosterDTO = PosterDTO(),
    val description: String? = ""
)