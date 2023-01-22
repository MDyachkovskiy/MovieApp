package com.example.kotlin_movieapp.repository.history

import com.example.kotlin_movieapp.model.movieDetailsResponse.MovieDTO

interface LocalRepository {
    fun getAllHistory() : List<MovieDTO>
    fun saveEntity(movie : MovieDTO)
}