package com.test.application.core.navigation

interface Navigator {
    fun navigateFromFavoritesToMovieDetails()
    fun navigateToMovieDetailsFragment(movieId: Int)
}