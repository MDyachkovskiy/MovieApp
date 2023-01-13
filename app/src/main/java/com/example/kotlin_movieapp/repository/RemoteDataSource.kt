package com.example.kotlin_movieapp.repository

import com.example.kotlin_movieapp.BuildConfig
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

private const val KINOPOISK_DOMAIN = "https://api.kinopoisk.dev"
private const val KINOPOISK_TOKEN = BuildConfig.KINOPOISK_API_KEY

class RemoteDataSource {

   fun getMovieDetails(requestLink: Int, callback: Callback) {

        val builder: Request.Builder = Request.Builder().apply{
            url(KINOPOISK_DOMAIN+"/movie?${KINOPOISK_TOKEN}&search=${requestLink}&field=id")
        }

        OkHttpClient().newCall(builder.build()).enqueue(callback)
    }
}