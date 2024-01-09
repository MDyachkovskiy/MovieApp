package com.test.application.movie_details.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.application.core.domain.movieDetail.Facts
import com.test.application.core.domain.movieDetail.MovieDetails
import com.test.application.core.utils.AppState.AppState
import com.test.application.core.utils.init
import com.test.application.core.view.BaseFragmentWithAppState
import com.test.application.movie_details.adapter.CommentsAdapter
import com.test.application.movie_details.databinding.FragmentMovieCommentsBinding

class MovieCommentsFragment : BaseFragmentWithAppState<AppState, MovieDetails, FragmentMovieCommentsBinding>(
    FragmentMovieCommentsBinding::inflate
) {
    private val viewModel: MovieDetailsViewModel by viewModels({requireParentFragment()})

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
        data.facts?.let { initCommentsRecyclerView(it) }
    }

    private fun initCommentsRecyclerView(facts: List<Facts>) {
        val commentsAdapter = CommentsAdapter(facts)
        binding.rvMovieComments.init(commentsAdapter, LinearLayoutManager.VERTICAL) }
    }
}