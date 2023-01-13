package com.example.kotlin_movieapp.repository

import com.example.kotlin_movieapp.models.DTO.MovieDTO


interface DetailsRepository {
    fun getMovieDetailsFromServer(requestLink: Int, callback: retrofit2.Callback<MovieDTO>)
}