package com.example.kotlin_movieapp.model.repository.movieDetails

import com.example.kotlin_movieapp.model.datasource.remote.movieDetailsResponse.MovieDTO
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import retrofit2.Callback


class DetailsRepositoryImpl (
    private val remoteDataSource: RemoteDataSource
) : DetailsRepository {

    override fun getMovieDetailsFromServer(movieId: Int?, callback: Callback<MovieDTO>) {
        remoteDataSource.getMovieDetails(movieId, callback)
    }
}