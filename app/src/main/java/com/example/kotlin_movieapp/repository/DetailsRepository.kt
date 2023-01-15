package com.example.kotlin_movieapp.repository

import com.example.kotlin_movieapp.models.collectionResponse.movieDetailsResponse.MovieDTO


interface DetailsRepository {
    fun getMovieDetailsFromServer(movieId: Int?, callback: retrofit2.Callback<MovieDTO>)
}