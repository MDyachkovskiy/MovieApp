package com.test.application.favorites

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.application.core.domain.favorites.FavoriteMovieItem
import com.test.application.core.navigation.Navigator
import com.test.application.core.utils.AppState.AppState
import com.test.application.core.utils.KEY_BUNDLE_MOVIE
import com.test.application.core.view.BaseFragmentWithAppState
import com.test.application.favorites.databinding.FragmentFavoritesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment :
    BaseFragmentWithAppState<AppState, List<FavoriteMovieItem>, FragmentFavoritesBinding>(
        FragmentFavoritesBinding::inflate
    ) {

    private val viewModel: FavoritesViewModel by viewModel()
    private lateinit var favoritesMovieAdapter: FavoriteMovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllFavorites()
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    override fun setupData(data: List<FavoriteMovieItem>) {
        initRV(data)
    }

    private fun initRV(movieData: List<FavoriteMovieItem>) {
        favoritesMovieAdapter = FavoriteMovieAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = favoritesMovieAdapter
        favoritesMovieAdapter.updateMovie(movieData)
        handleNavigationListener()
        handleFavoriteCheckBox()
    }

    private fun handleNavigationListener() {
        favoritesMovieAdapter.listener = { movieId ->
            val bundle = bundleOf(KEY_BUNDLE_MOVIE to movieId)
            (activity as Navigator).navigateToMovieDetailsFragment(bundle)
        }
    }

    private fun handleFavoriteCheckBox() {
        favoritesMovieAdapter.onFavoriteChanged = { movieId, isFavorite ->
            if (!isFavorite) {
                removeItemAndUpdate(movieId)
            }
        }
    }

    private fun removeItemAndUpdate(movieId: Int) {
        viewModel.deleteFavoriteMovie(movieId)
        favoritesMovieAdapter.removeItem(movieId)
    }
}