package com.example.kotlin_movieapp.models

import android.content.res.Resources
import com.example.kotlin_movieapp.R

class MovieSourceImpl (private val resources: Resources) : MovieSource {

    companion object {

        private var movieSource: MutableList<Movie> = ArrayList(7)

    }

    private val topMovieImages: IntArray
    get() {
        val pictures = resources.obtainTypedArray(R.array.MoviePosters)
        val length = pictures.length()
        val answer = IntArray(length)
        for (i in 0 until length) {
            answer[i] = pictures.getResourceId(i,0)
        }
        return answer
    }

   fun getImages() : IntArray = topMovieImages;

    override fun getMovie(position: Int): Movie = movieSource[position]

    override fun size(): Int = movieSource.size

    override fun indexOf(movie: Movie): Int = movieSource.indexOf(movie)

}

