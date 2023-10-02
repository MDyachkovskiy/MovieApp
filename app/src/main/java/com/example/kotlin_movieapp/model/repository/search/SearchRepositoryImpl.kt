package com.example.kotlin_movieapp.model.repository.search

import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import com.example.kotlin_movieapp.utils.REQUEST_ERROR


class SearchRepositoryImpl (
    private val remoteDataSource: RemoteDataSource
) : SearchRepository {

    override suspend fun getSearchCollection(name: String): AppState {
        return try {
            AppState.Success(remoteDataSource.getSearchCollection(name))
        } catch (e: Throwable) {
            AppState.Error(Throwable(e.message ?: REQUEST_ERROR))
        }
    }
}