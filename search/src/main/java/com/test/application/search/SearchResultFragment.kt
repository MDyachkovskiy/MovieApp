package com.test.application.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.application.KEY_QUERY
import com.test.application.core.domain.searchCollection.SearchMovie
import com.test.application.core.navigation.Navigator
import com.test.application.core.utils.KEY_BUNDLE_MOVIE
import com.test.application.core.utils.init
import com.test.application.core.view.BaseFragment
import com.test.application.search.adapter.SearchMovieAdapter
import com.test.application.search.databinding.FragmentSearchResultBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class SearchResultFragment: BaseFragment<FragmentSearchResultBinding>(
    FragmentSearchResultBinding::inflate
) {
    private lateinit var movieAdapter: SearchMovieAdapter
    private val viewModel: SearchViewModel by activityViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val query = arguments?.getString(KEY_QUERY) ?: ""
        viewModel.getSearchCollection(query)
        observeSearchResult()
    }

    private fun observeSearchResult() {
        viewModel.searchResultLiveData.observe(viewLifecycleOwner) { pagingData ->
            Log.d("@@@", "Observing paging data: $pagingData")
            initRV(pagingData)
        }
    }

    private fun initRV(movieData: PagingData<SearchMovie>) {
        movieAdapter = SearchMovieAdapter()
        movieAdapter.submitData(viewLifecycleOwner.lifecycle, movieData)
        movieAdapter.listener = { movieId ->
            val bundle = bundleOf(KEY_BUNDLE_MOVIE to movieId)
            (activity as Navigator).navigateToMovieDetailsFragment(bundle)
        }
        binding.rvSearch.init(movieAdapter, LinearLayoutManager.VERTICAL)
    }
}