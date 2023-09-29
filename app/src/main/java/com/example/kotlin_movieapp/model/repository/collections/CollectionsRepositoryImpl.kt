package com.example.kotlin_movieapp.model.repository.collections

import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.Top250Response
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.TopTvShowsResponse
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.UpComingResponse
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
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

    override fun getUpComingCollectionFromServer(callback: Callback<UpComingResponse>) {
        remoteDataSource.getUpComingCollection(callback)
    }
}