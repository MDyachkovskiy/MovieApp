package com.test.application.core.domain.personDetail


data class PersonDetails(
    val person: List<Person> = listOf(),
    val limit: Int = 0,
    val page: Int = 0,
    val pages: Int = 0,
    val total: Int = 0
)