package com.example.kotlin_movieapp.model.datasource.remote

import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.SearchResponse
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.Top250Response
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.TopTvShowsResponse
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.UpComingResponse
import com.example.kotlin_movieapp.model.datasource.remote.movieDetailsResponse.MovieDTO
import com.example.kotlin_movieapp.model.datasource.remote.personDetailsResponse.PersonDTO
import com.example.kotlin_movieapp.utils.AGE_RATING
import com.example.kotlin_movieapp.utils.ENLISH_NAME_FIELD
import com.example.kotlin_movieapp.utils.KINOPOISK_DOMAIN
import com.example.kotlin_movieapp.utils.NAME_FIELD
import com.example.kotlin_movieapp.utils.RATING_FIELD
import com.example.kotlin_movieapp.utils.SORT_FIELD

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    private val kinopoiskAPI = Retrofit.Builder()
        .baseUrl(KINOPOISK_DOMAIN)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(createOkHttpClient())
        .build().create(KinopoiskAPI::class.java)

   fun getMovieDetails(movieId: Int?, callback: Callback<MovieDTO>) {
        kinopoiskAPI.getMovie(movieId).enqueue(callback)
    }

    fun getPersonDetails(personId: Int?, callback: Callback<PersonDTO>) {
        kinopoiskAPI.getPerson(personId).enqueue(callback)
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

    fun getAdultCyrillicSearchCollection(rating: Int?, name: String,
                                         callback: Callback<SearchResponse>
    ){
        kinopoiskAPI.getAdultCyrillicSearchCollection(
            RATING_FIELD,
            "$rating-10",
            NAME_FIELD,
            name,
            RATING_FIELD,
            -1,
            AGE_RATING,
            6,
            AGE_RATING,
            12,
            AGE_RATING,
            16,
            AGE_RATING,
            18,
            AGE_RATING,
            SORT_FIELD
        ).enqueue(callback)
    }

    fun getCyrillicSearchCollection(rating: Int?, name: String,
                                    callback: Callback<SearchResponse>
    ){
        kinopoiskAPI.getCyrillicSearchCollection(
            RATING_FIELD,
            "$rating-10",
            NAME_FIELD,
            name,
            RATING_FIELD,
            -1,
            AGE_RATING,
            6,
            AGE_RATING,
            12,
            AGE_RATING,
            16,
            AGE_RATING,
            SORT_FIELD
        ).enqueue(callback)
    }

    fun getAdultLatinSearchCollection(rating: Int?, name: String,
                                      callback: Callback<SearchResponse>
    ){
        kinopoiskAPI.getAdultLatinSearchCollection(
            RATING_FIELD,
            "$rating-10",
            ENLISH_NAME_FIELD,
            name,
            RATING_FIELD,
            -1,
            AGE_RATING,
            6,
            AGE_RATING,
            12,
            AGE_RATING,
            16,
            AGE_RATING,
            18,
            AGE_RATING,
            SORT_FIELD
        ).enqueue(callback)
    }

    fun getLatinSearchCollection(rating: Int?, name: String,
                                 callback: Callback<SearchResponse>
    ){
        kinopoiskAPI.getLatinSearchCollection(
            RATING_FIELD,
            "$rating-10",
            ENLISH_NAME_FIELD,
            name,
            RATING_FIELD,
            -1,
            AGE_RATING,
            6,
            AGE_RATING,
            12,
            AGE_RATING,
            16,
            AGE_RATING,
            SORT_FIELD
        ).enqueue(callback)
    }

    private fun createOkHttpClient (): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        return httpClient.build()
    }
}