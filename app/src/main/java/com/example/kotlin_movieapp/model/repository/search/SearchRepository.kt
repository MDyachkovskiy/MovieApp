package com.example.kotlin_movieapp.model.repository.search

import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.SearchResponse

interface SearchRepository {
    fun getAdultLatinSearchResultFromServer(
        rating: Int?,
        name: String,
        callback: retrofit2.Callback<SearchResponse>
    )

    fun getLatinSearchResultFromServer(
        rating: Int?,
        name: String,
        callback: retrofit2.Callback<SearchResponse>
    )

    fun getAdultCyrillicSearchResultFromServer(
        rating: Int?,
        name: String,
        callback: retrofit2.Callback<SearchResponse>
    )

    fun getCyrillicSearchResultFromServer(
        rating: Int?,
        name: String,
        callback: retrofit2.Callback<SearchResponse>
    )
}