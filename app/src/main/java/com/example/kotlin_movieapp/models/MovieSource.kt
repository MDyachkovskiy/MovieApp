package com.example.kotlin_movieapp.models


interface MovieSource {
    fun getMovie(position : Int) : Movie
    fun size() : Int
    fun indexOf(movie : Movie) : Int
}