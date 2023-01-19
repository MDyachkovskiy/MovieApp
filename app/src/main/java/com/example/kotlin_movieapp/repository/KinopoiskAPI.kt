package com.example.kotlin_movieapp.repository

import com.example.kotlin_movieapp.BuildConfig
import com.example.kotlin_movieapp.models.collectionResponse.movieDetailsResponse.MovieDTO
import com.example.kotlin_movieapp.models.collectionResponse.Top250Response
import com.example.kotlin_movieapp.models.collectionResponse.TopTvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val KINOPOISK_TOKEN = BuildConfig.KINOPOISK_API_KEY
private const val MOVIE_END_POINT = "movie?$KINOPOISK_TOKEN&field=id"
private const val TOP250_END_POINT =
    "movie/?$KINOPOISK_TOKEN&field=top250&search=!null&sortField=top250&field=type&search=movie&moviesLimit=20&selectFields=id name top250 poster"
private const val TOP_TV_SHOWS_END_POINT =
    "movie/?$KINOPOISK_TOKEN&field=top250&search=!null&sortField=top250&field=type&search=tv_series&selectFields=id name top250 poster"


interface KinopoiskAPI {
    @GET(MOVIE_END_POINT)
    fun getMovie(
        @Query("search") id : Int?
    ) : Call<MovieDTO>

    @GET(TOP250_END_POINT)
    fun getTop250Collection(
    ) : Call<Top250Response>

    @GET(TOP_TV_SHOWS_END_POINT)
    fun getTopTvShowsCollection(
    ) : Call<TopTvShowsResponse>
}
