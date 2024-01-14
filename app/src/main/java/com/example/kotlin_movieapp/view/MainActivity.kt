package com.example.kotlin_movieapp.view

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.kotlin_movieapp.R
import com.example.kotlin_movieapp.databinding.ActivityMainBinding
import com.example.kotlin_movieapp.service.ConnectivityReceiver
import com.test.application.core.navigation.BackPressedHandler
import com.test.application.core.navigation.Navigator
import com.test.application.core.navigation.OnBackPressInPersonDetails
import com.test.application.core.utils.MOVIE_DETAILS_TAG
import com.test.application.core.utils.PERSON_DETAILS_TAG
import com.test.application.movie_details.view.MovieDetailsFragmentNew
import com.test.application.person_details.PersonDetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator, BackPressedHandler, OnBackPressInPersonDetails {

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
        var movieDetailsFragment = MovieDetailsFragmentNew().apply {
            arguments = bundle
        }

        val detailsContainer = binding.detailsContainer
        detailsContainer.visibility = View.VISIBLE
        detailsContainer.isFocusable = true

        supportFragmentManager.beginTransaction()
            .replace(detailsContainer.id, movieDetailsFragment, MOVIE_DETAILS_TAG)
            .addToBackStack(MOVIE_DETAILS_TAG)
            .commit()
    }

    override fun navigateToPersonDetailsFragment(bundle: Bundle) {
        val personDetailsFragment = PersonDetailsFragment().apply {
            arguments = bundle
        }

        val detailsContainer = binding.detailsContainer

        supportFragmentManager.beginTransaction()
            .add(detailsContainer.id, personDetailsFragment, PERSON_DETAILS_TAG)
            .addToBackStack(PERSON_DETAILS_TAG)
            .commit()
    }

    override fun onBackButtonPressedInMovieDetails() {
        if (supportFragmentManager.findFragmentByTag(MOVIE_DETAILS_TAG) != null) {
            supportFragmentManager
                .popBackStack(MOVIE_DETAILS_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            onDetailsFragmentClosed()
        }
    }

    private fun onDetailsFragmentClosed() {
        binding.detailsContainer.apply {
            visibility = View.GONE
            isFocusable = false
        }
    }

    override fun onBackButtonPressedInPersonDetails() {
        if (supportFragmentManager.findFragmentByTag(PERSON_DETAILS_TAG) != null) {
            supportFragmentManager
                .popBackStack(PERSON_DETAILS_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}