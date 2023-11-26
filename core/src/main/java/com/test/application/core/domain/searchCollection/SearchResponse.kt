package com.test.application.core.domain.searchCollection


data class SearchResponse(
    val movies: List<SearchMovie> = listOf(),
    val limit: Int = 0,
    val page: Int = 0,
    val pages: Int = 0,
    val total: Int = 0
)