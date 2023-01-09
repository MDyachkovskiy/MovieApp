package com.example.kotlin_movieapp.repository

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.kotlin_movieapp.BuildConfig
import com.example.kotlin_movieapp.models.DTO.MovieDTO
import com.example.kotlin_movieapp.utils.KINOPOSIK_DOMAIN
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MovieLoader(
    private val movieId: Int,
    private val listener: MovieLoaderListener,
) {
    interface MovieLoaderListener {
        fun onLoaded(movieDTO: MovieDTO)
        fun onFailed(throwable: Throwable)
    }

    private val KINOPOISK_TOKEN = BuildConfig.KINOPOISK_API_KEY
    private val KINOPOISK_DOMAIN = KINOPOSIK_DOMAIN

    fun loadMovie() {

        try {

            val uri = URL(
                "${KINOPOISK_DOMAIN}movie?token=${KINOPOISK_TOKEN}&search=${movieId}&field=id")

            Thread {
                lateinit var urlConnection: HttpsURLConnection

                try {
                    urlConnection = (uri.openConnection() as HttpsURLConnection).apply {
                        requestMethod = "GET"
                        readTimeout = 10000
                    }

                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val response = getLines(bufferedReader)

                    val movieDTO: MovieDTO = Gson().fromJson(response, MovieDTO::class.java)

                    Handler(Looper.getMainLooper()).post {
                        listener.onLoaded(movieDTO)
                    }

                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()

                    Handler(Looper.getMainLooper()).post {
                        listener.onFailed(e)
                    }
                } finally {
                    urlConnection.disconnect()
                }
            }.start()

        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
            listener.onFailed(e)
        }
    }

    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }
}