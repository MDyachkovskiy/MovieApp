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
import com.test.application.home.databinding.FragmentTvshowsBinding
import com.test.application.home.navigation.MovieListFragmentHandler
import com.test.application.home.util.MovieDataType
import com.test.application.home.util.MovieType
import com.test.application.home.util.RecyclerViewType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopTvShowsFragment : BaseFragment<FragmentTvshowsBinding>(
    FragmentTvshowsBinding::inflate
) {

    companion object {
        fun newInstance() = TopTvShowsFragment()
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
        binding.chipFilter.setOnCheckedStateChangeListener{_, checkedIds ->
            val movieType = when {
                binding.chipNetflix.id in checkedIds -> MovieType.NETFLIX
                binding.chipPrime.id in checkedIds -> MovieType.PRIME
                binding.chipHbo.id in checkedIds -> MovieType.HBO
                binding.chipDisney.id in checkedIds -> MovieType.DISNEY
                binding.chipApple.id in checkedIds -> MovieType.APPLE
                else -> MovieType.MOVIE
            }
            viewModel.loadTopTvShows(movieType)
        }
    }

    private fun initViewModel() {
        viewModel.topTvShowsLiveData.observe(viewLifecycleOwner) { pagingData ->
            movieAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        }
        viewModel.loadTopTvShows(MovieType.NETFLIX)
    }

    private fun initTextButton() {
        binding.categoryTitle.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_MOVIE_TYPE to MovieDataType.TOP_TV_SHOWS.name)
            (parentFragment as MovieListFragmentHandler).showMovieList(bundle)
        }
    }

    private fun initRV() {
        movieAdapter = MovieCollectionAdapter().apply {
            setViewType(RecyclerViewType.HORIZONTAL_LIST)
        }

        binding.rvTvShows.init(movieAdapter, LinearLayoutManager.HORIZONTAL)

        movieAdapter.listener = { movieId ->
            val bundle = bundleOf(KEY_BUNDLE_MOVIE to movieId)
            (activity as Navigator).navigateToMovieDetailsFragment(bundle)
        }
    }

    override fun onDestroyView() {
        movieAdapter.listener = null
        binding.rvTvShows.adapter = null
        super.onDestroyView()
    }
}