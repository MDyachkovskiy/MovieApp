package com.example.kotlin_movieapp.repository

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.kotlin_movieapp.BuildConfig
import com.example.kotlin_movieapp.models.MovieDTO
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

    private val KINOPOISK_API_KEY = BuildConfig.KINOPOISK_API_KEY

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadMovie() {

        try {

            val uri = URL(
                "https://api.kinopoisk.dev/movie?token=${KINOPOISK_API_KEY}&search=${movieId}&field=id")

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

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }
}