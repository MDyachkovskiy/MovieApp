package com.example.kotlin_movieapp.repository

import com.example.kotlin_movieapp.models.DTO.MovieDTO
import com.example.kotlin_movieapp.models.Movie

interface Repository {
    fun getMovieFromServer(movieId: Int) : MovieDTO
    fun getMovieFromLocalStorage() : List<Movie>
}