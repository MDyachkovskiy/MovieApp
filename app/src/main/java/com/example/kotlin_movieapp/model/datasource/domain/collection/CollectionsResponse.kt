package com.example.kotlin_movieapp.model.datasource.domain.collection

import com.google.gson.annotations.SerializedName

data class CollectionsResponse(
    @SerializedName("docs")
    val movie: List<Movie> = listOf(),
    val limit: Int = 0,
    val page: Int = 0,
    val pages: Int = 0,
    val total: Int = 0
)