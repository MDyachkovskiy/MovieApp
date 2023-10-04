package com.example.kotlin_movieapp.model.datasource.domain.personDetail

data class PersonDetailsResponse(
    val docs: List<Doc> = listOf(),
    val limit: Int = 0,
    val page: Int = 0,
    val pages: Int = 0,
    val total: Int = 0
)