package com.test.application.home.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.application.core.navigation.Navigator
import com.test.application.core.utils.KEY_BUNDLE_MOVIE
import com.test.application.core.utils.KEY_BUNDLE_MOVIE_TYPE
import com.test.application.core.utils.init
import com.test.application.core.view.BaseFragment
import com.test.application.home.adapter.MovieCollectionAdapter
import com.test.application.home.databinding.FragmentUpcomingBinding
import com.test.application.home.navigation.MovieListFragmentHandler
import com.test.application.home.util.MovieDataType
import com.test.application.home.util.MovieType
import com.test.application.home.util.RecyclerViewType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpComingMovieFragment : BaseFragment<FragmentUpcomingBinding>(
    FragmentUpcomingBinding::inflate
) {

    companion object {
        fun newInstance() = UpComingMovieFragment()
    }

    private val viewModel: HomeSharedViewModel by viewModels({requireParentFragment()})
    private lateinit var movieAdapter: MovieCollectionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        initTextButton()
        initViewModel()
        initChips()
    }

    private fun initChips() {
        binding.chipFilter.setOnCheckedStateChangeListener { _, checkedIds ->
            val movieType = when {
                binding.chipMovie.id in checkedIds -> MovieType.MOVIE
                binding.chipTvSeries.id in checkedIds -> MovieType.TV_SERIES
                binding.chipCartoon.id in checkedIds -> MovieType.CARTOON
                else -> MovieType.MOVIE
            }
            viewModel.loadUpcomingMovies(movieType)
        }
    }

    private fun initViewModel() {
        viewModel.upComingLiveData.observe(viewLifecycleOwner) { pagingData ->
            movieAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        }
        viewModel.loadUpcomingMovies(MovieType.MOVIE)
    }

    private fun initTextButton() {
        binding.categoryTitle.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_MOVIE_TYPE to MovieDataType.UPCOMING_MOVIES.name)
            (parentFragment as MovieListFragmentHandler).showMovieList(bundle)
        }
    }

    private fun initRV() {
        movieAdapter = MovieCollectionAdapter().apply {
            setViewType(RecyclerViewType.HORIZONTAL_LIST)
        }

        binding.RVUpComing.init(movieAdapter, LinearLayoutManager.HORIZONTAL)
        movieAdapter.listener = { movieId ->
            val bundle = bundleOf(KEY_BUNDLE_MOVIE to movieId)
            (activity as Navigator).navigateToMovieDetailsFragment(bundle)
        }
    }
}