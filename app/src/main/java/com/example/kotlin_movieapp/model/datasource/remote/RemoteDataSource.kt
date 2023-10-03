package com.example.kotlin_movieapp.model.datasource.remote

import com.example.kotlin_movieapp.model.datasource.domain.collection.CollectionsResponse
import com.example.kotlin_movieapp.model.datasource.domain.movieDetail.MovieDetailsResponse
import com.example.kotlin_movieapp.model.datasource.domain.personDetail.PersonDetailsResponse
import com.example.kotlin_movieapp.utils.KINOPOISK_DOMAIN
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    private val kinopoiskAPI = Retrofit.Builder()
        .baseUrl(KINOPOISK_DOMAIN)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(createOkHttpClient())
        .build().create(KinopoiskAPI::class.java)

    suspend fun getMovieDetails(movieId: Int?): MovieDetailsResponse {
        return kinopoiskAPI.getMovieAsync(id = movieId).await()
    }

    suspend fun getPersonDetails(personId: Int?): PersonDetailsResponse {
        return kinopoiskAPI.getPersonAsync(id = personId).await()
    }

    suspend fun getSearchCollection(name: String): CollectionsResponse{
        return kinopoiskAPI.getSearchMovieAsync(query = name).await()
    }

    fun getKinopoiskAPI(): KinopoiskAPI {
        return kinopoiskAPI
    }

    private fun createOkHttpClient (): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }
}