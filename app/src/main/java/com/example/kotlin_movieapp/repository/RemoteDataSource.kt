package com.example.kotlin_movieapp.repository

import com.example.kotlin_movieapp.models.DTO.MovieDTO
import com.example.kotlin_movieapp.models.Top250Response
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val KINOPOISK_DOMAIN = "https://api.kinopoisk.dev/"

class RemoteDataSource {

    private val kinopoiskAPI = Retrofit.Builder()
        .baseUrl(KINOPOISK_DOMAIN)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(createOkHttpClient())
        .build().create(KinopoiskAPI::class.java)

   fun getMovieDetails(movieId: Int, callback: Callback<MovieDTO>) {
        kinopoiskAPI.getMovie(movieId).enqueue(callback)
    }

    fun getTop250Collection(callback: Callback<Top250Response>){
        kinopoiskAPI.getTop250Collection().enqueue(callback)
    }

    private fun createOkHttpClient (): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        return httpClient.build()
    }


}