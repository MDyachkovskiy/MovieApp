package com.example.kotlin_movieapp.view.favorites

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_movieapp.databinding.FragmentFavoritesBinding
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.local.room.favorites.FavoriteMovieItem
import com.example.kotlin_movieapp.utils.init
import com.example.kotlin_movieapp.view.base.BaseFragment

class FavoritesFragment : BaseFragment<AppState,List<FavoriteMovieItem>, FragmentFavoritesBinding>(
    FragmentFavoritesBinding::inflate
)
 {

    private val viewModel: FavoritesViewModel by lazy {
        ViewModelProvider (this)[FavoritesViewModel::class.java]
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Thread {
            viewModel.getAllFavorites()
        }.start()

        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
    }
     override fun setupData(data: List<FavoriteMovieItem>) {
         initRV(data)
     }

     private fun initRV(movieData: List<FavoriteMovieItem>) {
        binding.recyclerView.init(FavoriteMovieAdapter(movieData), LinearLayoutManager.VERTICAL)
    }
}