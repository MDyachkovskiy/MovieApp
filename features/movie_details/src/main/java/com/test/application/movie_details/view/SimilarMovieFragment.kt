package com.test.application.movie_details.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.application.core.domain.movieDetail.MovieDetails
import com.test.application.core.domain.movieDetail.SimilarMovy
import com.test.application.core.navigation.Navigator
import com.test.application.core.utils.AppState.AppState
import com.test.application.core.utils.KEY_BUNDLE_MOVIE
import com.test.application.core.utils.init
import com.test.application.core.view.BaseFragmentWithAppState
import com.test.application.movie_details.adapter.SimilarMovieAdapter
import com.test.application.movie_details.databinding.FragmentSimilarMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SimilarMovieFragment : BaseFragmentWithAppState<AppState, MovieDetails, FragmentSimilarMovieBinding>(
    FragmentSimilarMovieBinding::inflate
) {
    private val viewModel: MovieDetailsViewModel by viewModels({requireParentFragment()})
    private var movieAdapter: SimilarMovieAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    override fun setupData(data: MovieDetails) {
        data.similarMovies?.let { initSimilarMovieRecyclerView(it) }
    }

    private fun initSimilarMovieRecyclerView(similarMovy: List<SimilarMovy>) {
        movieAdapter = SimilarMovieAdapter(similarMovy)
        movieAdapter?.listener = { movieId ->
            val bundle = bundleOf(KEY_BUNDLE_MOVIE to movieId)
            (activity as Navigator).navigateToMovieDetailsFragment(bundle)
        }
        movieAdapter?.let {
            binding.rvSimilarMovie.init(it, LinearLayoutManager.VERTICAL)
        }
    }

    override fun onDestroyView() {
        movieAdapter?.listener = null
        binding.rvSimilarMovie.adapter = null
        super.onDestroyView()
    }
}