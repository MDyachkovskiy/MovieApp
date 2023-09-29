package com.example.kotlin_movieapp.model.repository.search

import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.SearchResponse
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import retrofit2.Callback


class SearchRepositoryImpl (
    private val remoteDataSource: RemoteDataSource
) : SearchRepository {

    override fun getAdultLatinSearchResultFromServer(
        rating: Int?,
        name: String,
        callback: Callback<SearchResponse>,
    ) {
        remoteDataSource.getAdultLatinSearchCollection(rating, name, callback)
    }

    override fun getLatinSearchResultFromServer(
        rating: Int?,
        name: String,
        callback: Callback<SearchResponse>,
    ) {
        remoteDataSource.getLatinSearchCollection(rating, name, callback)
    }

    override fun getAdultCyrillicSearchResultFromServer(
        rating: Int?,
        name: String,
        callback: Callback<SearchResponse>,
    ) {
        remoteDataSource.getAdultCyrillicSearchCollection(rating, name, callback)
    }

    override fun getCyrillicSearchResultFromServer(
        rating: Int?,
        name: String,
        callback: Callback<SearchResponse>,
    ) {
        remoteDataSource.getCyrillicSearchCollection(rating, name, callback)
    }


}