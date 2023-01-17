package com.example.kotlin_movieapp.repository

import com.example.kotlin_movieapp.model.collectionResponse.Top250Response
import com.example.kotlin_movieapp.model.collectionResponse.TopTvShowsResponse
import retrofit2.Callback

class CollectionsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : CollectionsRepository {

    override fun getTop250CollectionFromServer(callback: Callback<Top250Response>) {
        remoteDataSource.getTop250Collection(callback)

    }

    override fun getTopTvShowsCollectionFromServer(callback: Callback<TopTvShowsResponse>) {
        remoteDataSource.getTopTvShowsCollection(callback)
    }


}