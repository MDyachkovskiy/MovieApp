package com.example.kotlin_movieapp.repository

import com.example.kotlin_movieapp.models.CollectionItem
import retrofit2.Callback

class CollectionsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : CollectionsRepository {

    override fun getTop250CollectionFromServer(
        callback: Callback<List<CollectionItem>>)
    {
        remoteDataSource.getTop250Collection(callback)
    }
}