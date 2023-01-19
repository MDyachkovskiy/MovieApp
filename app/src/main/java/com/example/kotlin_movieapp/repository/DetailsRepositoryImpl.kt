package com.example.kotlin_movieapp.repository

import com.example.kotlin_movieapp.models.collectionResponse.movieDetailsResponse.MovieDTO
import retrofit2.Callback


class DetailsRepositoryImpl (
    private val remoteDataSource: RemoteDataSource
) : DetailsRepository {

    override fun getMovieDetailsFromServer(movieId: Int?, callback: Callback<MovieDTO>) {
        remoteDataSource.getMovieDetails(movieId, callback)
    }
}