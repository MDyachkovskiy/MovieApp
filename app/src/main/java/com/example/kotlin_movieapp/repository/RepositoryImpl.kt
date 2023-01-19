package com.example.kotlin_movieapp.repository

import com.example.kotlin_movieapp.models.Movie
import com.example.kotlin_movieapp.models.getTopMovies


class RepositoryImpl() : Repository {

    override fun getMovieFromServer() : Movie {
        return Movie();
    }

    override fun getMovieFromLocalStorage() : List<Movie> {
        Thread.sleep(2000L)
        return getTopMovies()
    }
}