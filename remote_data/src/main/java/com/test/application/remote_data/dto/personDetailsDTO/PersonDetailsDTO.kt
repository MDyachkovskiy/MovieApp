package com.test.application.remote_data.dto.personDetailsDTO

import com.google.gson.annotations.SerializedName

data class PersonDetailsDTO(
    @SerializedName("docs")
    val person: List<PersonDTO> = listOf(),
    val limit: Int = 0,
    val page: Int = 0,
    val pages: Int = 0,
    val total: Int = 0
)