package com.test.application.core.domain.collection

data class Movie(
    val id: Int = 0,
    val name: String = "",
    val poster: Poster = Poster(),
    val description: String? = ""
)