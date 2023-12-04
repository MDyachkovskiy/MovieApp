package com.example.kotlin_movieapp.view

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.kotlin_movieapp.R
import com.example.kotlin_movieapp.databinding.ActivityMainBinding
import com.example.kotlin_movieapp.service.ConnectivityReceiver
import com.test.application.core.navigation.Navigator
import com.test.application.favorites.FavoritesFragment
import com.test.application.search.SearchFragment
import com.test.application.settings.SettingsFragment
import com.test.application.home.HomeFragment

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding : ActivityMainBinding
    private val connectivityReceiver = ConnectivityReceiver()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
            .also{ setContentView(it.root) }

        registerReceiver(connectivityReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
        navController = navHostFragment.navController

        binding.navigationMenu.setOnItemSelectedListener {

            when(it.itemId) {
                R.id.nav_collections -> replaceFragment(HomeFragment())
                R.id.nav_favorites -> replaceFragment(FavoritesFragment())
                R.id.nav_search -> replaceFragment(SearchFragment())
                R.id.nav_settings -> replaceFragment(SettingsFragment())

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

    override fun navigateFromFavoritesToMovieDetails() {
        TODO("Not yet implemented")
    }
}