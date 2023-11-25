package com.test.application.remote_data.dto.collection

data class Movie(
    val id: Int = 0,
    val name: String = "",
    val poster: Poster = Poster(),
    val description: String? = ""
)