package com.test.application.search

import android.os.Bundle
import android.view.View
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.application.core.domain.searchCollection.SearchMovie
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
        viewModel.searchResultLiveData.observe(viewLifecycleOwner) { pagingData ->
            initRV(pagingData)
        }
    }

    private fun initRV(movieData: PagingData<SearchMovie>) {
        movieAdapter = SearchMovieAdapter()
        movieAdapter.submitData(viewLifecycleOwner.lifecycle, movieData)
        binding.rvSearch.init(movieAdapter, LinearLayoutManager.VERTICAL)
    }
}