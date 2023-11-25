package com.test.application.core.domain.personDetail

import com.google.gson.annotations.SerializedName

data class PersonDetailsResponse(
    @SerializedName("docs")
    val person: List<Person> = listOf(),
    val limit: Int = 0,
    val page: Int = 0,
    val pages: Int = 0,
    val total: Int = 0
)