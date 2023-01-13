package com.example.kotlin_movieapp.repository

import okhttp3.Callback

class DetailsRepositoryImpl (
    private val remoteDataSource: RemoteDataSource
) : DetailsRepository {

    override fun getMovieDetails(requestLink: Int, callback: Callback) {
        remoteDataSource.getMovieDetails(requestLink, callback)
    }
}