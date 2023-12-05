package com.test.application.home.topTvShows

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.application.core.navigation.Navigator
import com.test.application.core.utils.KEY_BUNDLE_MOVIE
import com.test.application.core.utils.init
import com.test.application.core.view.BaseFragment
import com.test.application.home.adapter.MovieCollectionAdapter
import com.test.application.home.databinding.FragmentTvshowsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopTvShowsFragment : BaseFragment<FragmentTvshowsBinding>(
    FragmentTvshowsBinding::inflate
) {

    companion object {
        fun newInstance() = TopTvShowsFragment()
    }

    private val viewModel: TopTvShowsViewModel by viewModel()
    private lateinit var movieAdapter: MovieCollectionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()

        viewModel.topTvShowsLiveData.observe(viewLifecycleOwner) { pagingData ->
            movieAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        }
    }

    private fun initRV() {
        movieAdapter = MovieCollectionAdapter()
        movieAdapter.listener = {
            (activity as? Navigator)?.navigateFromFavoritesToMovieDetails()
        }
        binding.RVTvShows.init(movieAdapter, LinearLayoutManager.HORIZONTAL)
        movieAdapter.listener = { movieId ->
            val bundle = bundleOf(KEY_BUNDLE_MOVIE to movieId)
            (activity as Navigator).navigateToMovieDetailsFragment(bundle)
        }
    }
}