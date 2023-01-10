package com.example.kotlin_movieapp.repository

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.kotlin_movieapp.BuildConfig
import com.example.kotlin_movieapp.models.DTO.MovieDTO
import com.example.kotlin_movieapp.models.Movie
import com.example.kotlin_movieapp.models.getTopMovies
import com.example.kotlin_movieapp.utils.KINOPOSIK_DOMAIN
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

private val KINOPOISK_TOKEN = BuildConfig.KINOPOISK_API_KEY
private val KINOPOISK_DOMAIN = KINOPOSIK_DOMAIN

class RepositoryImpl () : Repository {

    override fun getMovieFromServer(movieId: Int) : MovieDTO {

        val uri = URL(
            "${KINOPOISK_DOMAIN}/movie?token=${KINOPOISK_TOKEN}&search=${movieId}&field=id")

        lateinit var urlConnection: HttpsURLConnection

        urlConnection = (uri.openConnection() as HttpsURLConnection).apply {
            requestMethod = "GET"
            readTimeout = 10000
        }

        val bufferedReader =
            BufferedReader(InputStreamReader(urlConnection.inputStream))
        val movieDTO: MovieDTO = Gson().fromJson(bufferedReader, MovieDTO::class.java)

        urlConnection.disconnect()

        return movieDTO;
    }


    override fun getMovieFromLocalStorage(): List<Movie> = getTopMovies()

}

