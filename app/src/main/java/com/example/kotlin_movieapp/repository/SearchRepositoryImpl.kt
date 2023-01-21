package com.example.kotlin_movieapp.repository

import com.example.kotlin_movieapp.model.collectionResponse.SearchResponse
import retrofit2.Callback


class SearchRepositoryImpl (
    private val remoteDataSource: RemoteDataSource
) : SearchRepository {

    override fun getSearchResultFromServer(
        rating: Int?,
        name: String,
        callback: Callback<SearchResponse>
    ) {
        remoteDataSource.getSearchCollection(rating, name, callback)
    }
}