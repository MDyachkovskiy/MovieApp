package com.test.application.favorites

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.application.core.domain.favorites.FavoriteMovieItem
import com.test.application.core.navigation.Navigator
import com.test.application.core.utils.AppState.AppState
import com.test.application.core.utils.init
import com.test.application.core.view.BaseFragmentWithAppState
import com.test.application.favorites.databinding.FragmentFavoritesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseFragmentWithAppState<AppState, List<FavoriteMovieItem>, FragmentFavoritesBinding>(
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
         favoritesMovieAdapter = FavoriteMovieAdapter(movieData)
         binding.recyclerView.init(favoritesMovieAdapter, LinearLayoutManager.VERTICAL)
         favoritesMovieAdapter.listener = {
             (activity as? Navigator)?.navigateFromFavoritesToMovieDetails()
         }
    }
}