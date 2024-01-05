package com.test.application.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.test.application.core.utils.MOVIE_LIST_TAG
import com.test.application.home.R
import com.test.application.home.databinding.FragmentHomeBinding
import com.test.application.home.navigation.MovieListFragmentHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), MovieListFragmentHandler {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeSharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val initiateViewModel = viewModel.top250LiveData
        childFragmentManager
            .beginTransaction()
            .replace(R.id.container_UpComing, UpComingMovieFragment.newInstance())
            .replace(R.id.container_Top250, Top250MovieFragment.newInstance())
            .replace(R.id.container_TvShows, TopTvShowsFragment.newInstance())
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showMovieList(bundle: Bundle) {
        activateMovieListContainer()
        childFragmentManager.beginTransaction()
            .replace(R.id.movie_list_container, MovieListFragment.newInstance(bundle))
            .addToBackStack(MOVIE_LIST_TAG)
            .commit()
    }

    private fun activateMovieListContainer() {
        binding.movieListContainer.visibility = View.VISIBLE
        binding.collectionsScrollView.isClickable = false
        binding.collectionsScrollView.isFocusable = false
    }

    fun restoreScrollView() {
        binding.movieListContainer.visibility = View.GONE
        binding.collectionsScrollView.isClickable = true
        binding.collectionsScrollView.isFocusable = true
    }
}