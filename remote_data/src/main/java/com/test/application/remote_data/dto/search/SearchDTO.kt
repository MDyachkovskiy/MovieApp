package com.test.application.remote_data.dto.search

import com.google.gson.annotations.SerializedName

data class SearchDTO(
    @SerializedName("docs")
    val movies: List<SearchMovieDTO> = listOf(),
    val limit: Int = 0,
    val page: Int = 0,
    val pages: Int = 0,
    val total: Int = 0
)