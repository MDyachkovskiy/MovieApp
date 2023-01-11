package com.example.kotlin_movieapp

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_movieapp.databinding.ActivityMainBinding
import com.example.kotlin_movieapp.repository.ConnectivityReceiver
import com.example.kotlin_movieapp.ui.main.movieList.MovieListFragment

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
                .replace(R.id.container, MovieListFragment.newInstance())
                .commitNow()
        }
    }

    override fun onDestroy() {
        unregisterReceiver(connectivityReceiver)
        super.onDestroy()
    }
}