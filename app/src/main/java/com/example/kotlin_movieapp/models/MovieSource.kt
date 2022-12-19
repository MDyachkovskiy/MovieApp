package com.example.kotlin_movieapp.models

import com.example.kotlin_movieapp.models.Movie

interface MovieSource {
    fun getMovie(position : Int) : Movie
    fun size() : Int
    fun indexOf(movie : Movie) : Int
}