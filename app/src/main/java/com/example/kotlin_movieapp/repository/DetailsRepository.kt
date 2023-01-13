package com.example.kotlin_movieapp.repository

import okhttp3.Callback

interface DetailsRepository {
    fun getMovieDetails(requestLink: Int, callback: Callback)
}