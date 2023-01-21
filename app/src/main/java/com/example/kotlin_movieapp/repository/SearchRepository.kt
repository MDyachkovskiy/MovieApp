package com.example.kotlin_movieapp.repository

import com.example.kotlin_movieapp.model.collectionResponse.SearchResponse

interface SearchRepository {
    fun getSearchResultFromServer(rating: Int?,
                                  name: String,
                                  callback: retrofit2.Callback<SearchResponse>)
}