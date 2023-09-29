package com.example.kotlin_movieapp.view.search

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_movieapp.databinding.FragmentSearchResultBinding
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.SearchResponse
import com.example.kotlin_movieapp.utils.init
import com.example.kotlin_movieapp.view.base.BaseFragment

class SearchResultFragment(
    val appState: AppState
) : BaseFragment<AppState, SearchResponse, FragmentSearchResultBinding>(
    FragmentSearchResultBinding::inflate
) {
    override fun setupData(data: SearchResponse) {
        initRV(data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderData(appState)
    }

    private fun initRV(movieData: SearchResponse) {
        val movieList = movieData.searchResults
        binding.searchRecyclerView.init(SearchMovieAdapter(movieList), LinearLayoutManager.VERTICAL)
    }
}