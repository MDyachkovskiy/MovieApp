package com.example.kotlin_movieapp.model.repository.movieDetails

import com.example.kotlin_movieapp.model.datasource.remote.movieDetailsResponse.MovieDTO

interface DetailsRepository {
    fun getMovieDetailsFromServer(movieId: Int?, callback: retrofit2.Callback<MovieDTO>)
}