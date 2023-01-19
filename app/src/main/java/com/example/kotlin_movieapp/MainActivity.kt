package com.example.kotlin_movieapp

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kotlin_movieapp.databinding.ActivityMainBinding
import com.example.kotlin_movieapp.repository.ConnectivityReceiver
import com.example.kotlin_movieapp.ui.main.favoritesList.FavoritesListFragment
import com.example.kotlin_movieapp.ui.main.movieList.MovieListFragment
import com.example.kotlin_movieapp.ui.main.search.SearchFragment

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

        binding.navigationMenu.setOnItemSelectedListener {

            when(it.itemId) {
                R.id.nav_collections -> replaceFragment(MovieListFragment())
                R.id.nav_favorites -> replaceFragment(FavoritesListFragment())
                R.id.nav_search -> replaceFragment(SearchFragment())
                R.id.nav_settings -> replaceFragment(SearchFragment())

                else -> {}
            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }

    override fun onDestroy() {
        unregisterReceiver(connectivityReceiver)
        super.onDestroy()
    }
}