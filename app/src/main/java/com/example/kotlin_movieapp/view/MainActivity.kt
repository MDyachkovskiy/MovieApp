package com.example.kotlin_movieapp.view

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.kotlin_movieapp.R
import com.example.kotlin_movieapp.databinding.ActivityMainBinding
import com.example.kotlin_movieapp.service.ConnectivityReceiver
import com.test.application.core.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        binding.navigationMenu.setOnItemSelectedListener { menuItem ->

            when(menuItem.itemId) {
                R.id.nav_collections -> navController.navigate(R.id.homeFragment)
                R.id.nav_favorites -> navController.navigate(R.id.favoritesFragment)
                R.id.nav_search -> navController.navigate(R.id.searchFragment)
                R.id.nav_settings -> navController.navigate(R.id.settingsFragment)
            }
            true
        }
    }

    override fun onDestroy() {
        unregisterReceiver(connectivityReceiver)
        super.onDestroy()
    }

    override fun navigateFromFavoritesToMovieDetails() {
        navController.navigate(R.id.action_favoritesFragment_to_movieDetailsFragment)
    }

    override fun navigateToMovieDetailsFragment(bundle: Bundle) {
        navController.navigate(R.id.movieDetailsFragment, bundle)
    }

    override fun navigateToPersonDetailsFragment(bundle: Bundle) {
        navController.navigate(R.id.personDetailsFragment, bundle)
    }
}