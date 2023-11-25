package com.test.application.remote_data.dto.collection

import com.google.gson.annotations.SerializedName

data class CollectionsDTO(
    @SerializedName("docs")
    val movie: List<Movie> = listOf(),
    val limit: Int = 0,
    val page: Int = 0,
    val pages: Int = 0,
    val total: Int = 0
)