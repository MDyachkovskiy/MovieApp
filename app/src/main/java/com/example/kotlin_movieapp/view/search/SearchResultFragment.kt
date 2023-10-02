package com.example.kotlin_movieapp.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_movieapp.databinding.FragmentSearchResultBinding
import com.example.kotlin_movieapp.model.datasource.domain.collection.CollectionsResponse
import com.example.kotlin_movieapp.utils.init

class SearchResultFragment(
    private val data: CollectionsResponse
) : Fragment() {

    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        initRV(data)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRV(movieData: CollectionsResponse) {
        val movieList = movieData.docs
        binding.searchRecyclerView.init(SearchMovieAdapter(movieList), LinearLayoutManager.VERTICAL)
    }
}