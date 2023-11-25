package com.test.application.core.domain.searchCollection

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("docs")
    val movies: List<Movie> = listOf(),
    val limit: Int = 0,
    val page: Int = 0,
    val pages: Int = 0,
    val total: Int = 0
)