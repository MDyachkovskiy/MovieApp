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
import com.test.application.home.databinding.FragmentTop250movieBinding
import com.test.application.home.navigation.MovieListFragmentHandler
import com.test.application.home.util.MovieDataType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Top250MovieFragment : BaseFragment<FragmentTop250movieBinding>(
    FragmentTop250movieBinding::inflate
) {
    companion object {
        fun newInstance() = Top250MovieFragment()
    }

    private val viewModel: HomeSharedViewModel by viewModels({requireParentFragment()})
    private lateinit var movieAdapter: MovieCollectionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        initTextButton()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.top250LiveData.observe(viewLifecycleOwner){ pagingData ->
            movieAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        }
        viewModel.loadTop250Movies()
    }

    private fun initTextButton() {
        binding.categoryTitle.setOnClickListener {
            val bundle = bundleOf(KEY_BUNDLE_MOVIE_TYPE to MovieDataType.TOP_250.name)
            (parentFragment as MovieListFragmentHandler).showMovieList(bundle)
        }
    }

    private fun initRV() {
        movieAdapter = MovieCollectionAdapter()

        binding.RVTop250.init(movieAdapter, LinearLayoutManager.HORIZONTAL)

        movieAdapter.listener = { movieId ->
            val bundle = bundleOf(KEY_BUNDLE_MOVIE to movieId)
            (activity as Navigator).navigateToMovieDetailsFragment(bundle)
        }
    }
}