package com.test.application.remote_data.dto.personDetailsDTO
data class PersonDetailsDTO(
    val person: List<Person> = listOf(),
    val limit: Int = 0,
    val page: Int = 0,
    val pages: Int = 0,
    val total: Int = 0
)