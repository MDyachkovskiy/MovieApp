package com.example.kotlin_movieapp.repository

import com.example.kotlin_movieapp.models.Movie
import com.example.kotlin_movieapp.models.MovieSource

interface Repository {
    fun getMovieFromServer() : Movie
    fun getMovieFromLocalStorage() : Movie
}