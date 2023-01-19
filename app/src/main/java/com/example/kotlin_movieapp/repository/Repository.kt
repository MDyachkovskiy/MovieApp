package com.example.kotlin_movieapp.repository

import com.example.kotlin_movieapp.models.Movie

interface Repository {
    fun getMovieFromServer() : Movie
    fun getMovieFromLocalStorage() : List<Movie>
}