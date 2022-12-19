package com.example.kotlin_movieapp.models

import android.content.res.Resources
import com.example.kotlin_movieapp.R

class MovieSourceImpl (private val resources: Resources) : MovieSource {

    companion object {
        private var movieSource: MutableList<Movie> = ArrayList(7)
    }

    fun init() : MovieSourceImpl{
        val movieNames = resources.getStringArray(R.array.MovieNames)
        val pictures = imageArray
        for (i in 0..6) {
            movieSource.add(Movie(i, pictures[i], movieNames[i]))
        }
        return this
    }

    private val imageArray: IntArray
    private get() {
        val pictures = resources.obtainTypedArray(R.array.MoviePosters)
        val length = pictures.length()
        val answer = IntArray(length)
        for (i in 0..length) {
            answer[i] = pictures.getResourceId(i,0)
        }
        return answer
    }

    override fun getMovie(position: Int): Movie {
        return movieSource[position]
    }

    override fun size(): Int {
        return movieSource.size
    }

    override fun indexOf(movie: Movie): Int {
        return movieSource.indexOf(movie)
    }
}

