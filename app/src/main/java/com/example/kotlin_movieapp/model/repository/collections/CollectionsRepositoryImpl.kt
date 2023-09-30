package com.example.kotlin_movieapp.model.repository.collections

import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import com.example.kotlin_movieapp.utils.REQUEST_ERROR

class CollectionsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : CollectionsRepository {

    override suspend fun getTop250CollectionFromServer(): AppState {
        return parseApiCall { remoteDataSource.getTop250Collection() }
    }

    override suspend fun getTopTvShowsCollectionFromServer(): AppState {
        return parseApiCall { remoteDataSource.getTopTvShowsCollection() }
    }

    override suspend fun getUpComingCollectionFromServer(): AppState {
       return parseApiCall { remoteDataSource.getUpComingCollection() }
    }
    private suspend fun <T> parseApiCall(apiCall: suspend() -> T): AppState {
        return try {
            AppState.Success(apiCall.invoke())
        } catch(e: Throwable) {
            AppState.Error(Throwable(e.message ?: REQUEST_ERROR))
        }
    }
}