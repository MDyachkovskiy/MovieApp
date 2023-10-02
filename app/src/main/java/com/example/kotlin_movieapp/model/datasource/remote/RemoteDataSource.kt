package com.example.kotlin_movieapp.model.datasource.remote

import com.example.kotlin_movieapp.model.datasource.domain.collection.CollectionsResponse
import com.example.kotlin_movieapp.model.datasource.domain.movieDetail.MovieDetailsResponse
import com.example.kotlin_movieapp.model.datasource.remote.personDetailsResponse.PersonDTO
import com.example.kotlin_movieapp.utils.KINOPOISK_DOMAIN
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
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

    fun getPersonDetails(personId: Int?, callback: Callback<PersonDTO>) {
        kinopoiskAPI.getPerson(personId).enqueue(callback)
    }

    suspend fun getTop250Collection(): CollectionsResponse {
        return kinopoiskAPI.getTop250CollectionAsync().await()
    }

    suspend fun getTopTvShowsCollection(): CollectionsResponse{
        return kinopoiskAPI.getTopTvShowsCollectionAsync().await()
    }

    suspend fun getUpComingCollection(): CollectionsResponse{
        return kinopoiskAPI.getUpComingCollectionAsync().await()
    }

    suspend fun getSearchCollection(name: String): CollectionsResponse{
        return kinopoiskAPI.getSearchMovieAsync(query = name).await()
    }

    private fun createOkHttpClient (): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }
}