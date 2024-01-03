package com.test.application.core.navigation

import android.os.Bundle

interface Navigator {
    fun navigateFromFavoritesToMovieDetails()
    fun navigateToMovieDetailsFragment(bundle: Bundle)
    fun navigateToPersonDetailsFragment(bundle: Bundle)
    fun navigateToMovieListFragment()
}