package com.example.kotlin_movieapp.repository

import com.example.kotlin_movieapp.BuildConfig
import com.example.kotlin_movieapp.models.DTO.MovieDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val KINOPOISK_TOKEN = BuildConfig.KINOPOISK_API_KEY
private const val MOVIE_END_POINT = "movie?$KINOPOISK_TOKEN&field=id"

interface KinopoiskAPI {
    @GET(MOVIE_END_POINT)
    fun getMovie(
        @Query("search") id : Int
    ) : Call<MovieDTO>
}