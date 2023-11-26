package com.test.application.core.domain.collection

data class Collections(
    val movie: List<Movie> = listOf(),
    val limit: Int = 0,
    val page: Int = 0,
    val pages: Int = 0,
    val total: Int = 0
)