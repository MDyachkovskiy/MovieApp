package com.example.kotlin_movieapp.ui.main.movieDetails

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.kotlin_movieapp.BuildConfig
import com.example.kotlin_movieapp.models.collectionResponse.movieDetailsResponse.MovieDTO
import com.example.kotlin_movieapp.utils.*
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MovieDetailsService(val name: String = "") : IntentService(name) {

    private val KINOPOISK_DOMAIN = KINOPOSIK_DOMAIN
    private val KINOPOISK_TOKEN = BuildConfig.KINOPOISK_API_KEY


    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            val movieId = it.getIntExtra(KEY_SERVICE_MOVIE_ID, 0)

            val uri = URL(
                "${KINOPOISK_DOMAIN}/movie?${KINOPOISK_TOKEN}&search=${movieId}&field=id")

            lateinit var urlConnection: HttpsURLConnection

            try {
                urlConnection = (uri.openConnection() as HttpsURLConnection).apply {
                    requestMethod = "GET"
                    readTimeout = 10000
                }

                val bufferedReader =
                    BufferedReader(InputStreamReader(urlConnection.inputStream))

                val movieDTO: MovieDTO = Gson().fromJson(bufferedReader, MovieDTO::class.java)
                val message = Intent(WAVE_SERVICE_BROADCAST)
                message.putExtra(KEY_SERVICE_TO_BR_MOVIE, movieDTO)
                LocalBroadcastManager.getInstance(this).sendBroadcast(message)

            } catch (e: Exception) {
                Log.e("", "Fail connection", e)
                e.printStackTrace()

            } finally {
                urlConnection.disconnect()
            }
        }
    }
}