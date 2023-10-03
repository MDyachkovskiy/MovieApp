package com.example.kotlin_movieapp.model.datasource.domain.searchCollection

data class SearchResponse(
    val docs: List<Doc> = listOf(),
    val limit: Int = 0,
    val page: Int = 0,
    val pages: Int = 0,
    val total: Int = 0
)