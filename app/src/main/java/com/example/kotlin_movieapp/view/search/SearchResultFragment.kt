package com.example.kotlin_movieapp.view.search

import android.os.Bundle
import android.view.View
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_movieapp.databinding.FragmentSearchResultBinding
import com.example.kotlin_movieapp.model.datasource.domain.searchCollection.Doc
import com.example.kotlin_movieapp.utils.init
import com.example.kotlin_movieapp.view.base.BaseFragment
import com.example.kotlin_movieapp.view.search.adapter.SearchMovieAdapter
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

    private fun initRV(movieData: PagingData<Doc>) {
        movieAdapter = SearchMovieAdapter()
        movieAdapter.submitData(viewLifecycleOwner.lifecycle, movieData)
        binding.searchRecyclerView.init(movieAdapter, LinearLayoutManager.VERTICAL)
    }
}