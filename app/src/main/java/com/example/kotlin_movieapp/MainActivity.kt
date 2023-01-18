package com.example.kotlin_movieapp

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_movieapp.databinding.ActivityMainBinding
import com.example.kotlin_movieapp.repository.ConnectivityReceiver
import com.example.kotlin_movieapp.ui.main.movieList.MovieListFragment
import com.example.kotlin_movieapp.ui.main.top250Movie.Top250MovieFragment
import com.example.kotlin_movieapp.ui.main.topTvShows.TopTvShowsFragment
import com.example.kotlin_movieapp.ui.main.upComing.UpComingMovieFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val connectivityReceiver = ConnectivityReceiver()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
            .also{ setContentView(it.root) }

        registerReceiver(connectivityReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, MovieListFragment.newInstance())
                .commitNow()
        }
    }

    override fun onDestroy() {
        unregisterReceiver(connectivityReceiver)
        super.onDestroy()
    }
}