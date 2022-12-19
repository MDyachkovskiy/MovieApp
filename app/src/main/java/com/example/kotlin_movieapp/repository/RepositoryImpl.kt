package com.example.kotlin_movieapp.repository


import com.example.kotlin_movieapp.models.Movie


class RepositoryImpl() : Repository {

    override fun getMovieFromServer() : Movie {
        return Movie();
    }

    override fun getMovieFromLocalStorage() : Movie {
        //return MovieSourceImpl(context.resources).init()
        return Movie();
    }
}