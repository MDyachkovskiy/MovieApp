package com.example.kotlin_movieapp.repository

import com.example.kotlin_movieapp.model.collectionResponse.SearchResponse
import com.example.kotlin_movieapp.model.collectionResponse.Top250Response
import com.example.kotlin_movieapp.model.collectionResponse.TopTvShowsResponse
import com.example.kotlin_movieapp.model.collectionResponse.UpComingResponse
import com.example.kotlin_movieapp.model.movieDetailsResponse.MovieDTO
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val KINOPOISK_DOMAIN = "https://api.kinopoisk.dev/"

private const val RATING_FIELD = "rating.kp"

private const val NAME_FIELD = "name"

private const val SORT_FIELD = "id name top250 poster type rating.kp"

class RemoteDataSource {

    private val kinopoiskAPI = Retrofit.Builder()
        .baseUrl(KINOPOISK_DOMAIN)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(createOkHttpClient())
        .build().create(KinopoiskAPI::class.java)

   fun getMovieDetails(movieId: Int?, callback: Callback<MovieDTO>) {
        kinopoiskAPI.getMovie(movieId).enqueue(callback)
    }

    fun getTop250Collection(callback: Callback<Top250Response>){
        kinopoiskAPI.getTop250Collection().enqueue(callback)
    }

    fun getTopTvShowsCollection(callback: Callback<TopTvShowsResponse>){
        kinopoiskAPI.getTopTvShowsCollection().enqueue(callback)
    }

    fun getUpComingCollection(callback: Callback<UpComingResponse>){
        kinopoiskAPI.getUpComingCollection().enqueue(callback)
    }

    fun getSearchCollection(rating: Int?, name: String,
        callback: Callback<SearchResponse>){
        kinopoiskAPI.getSearchCollection(
            RATING_FIELD,
            "$rating-10",
            NAME_FIELD,
            name,
            RATING_FIELD,
            -1,
            SORT_FIELD
        ).enqueue(callback)
    }

    private fun createOkHttpClient (): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        return httpClient.build()
    }
}