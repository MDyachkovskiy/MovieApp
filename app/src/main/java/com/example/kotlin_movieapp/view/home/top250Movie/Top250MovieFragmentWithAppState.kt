package com.example.kotlin_movieapp.view.home.top250Movie

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_movieapp.databinding.FragmentTop250movieBinding
import com.example.kotlin_movieapp.utils.init
import com.example.kotlin_movieapp.view.base.BaseFragment
import com.example.kotlin_movieapp.view.home.adapter.MovieCollectionAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class Top250MovieFragmentWithAppState : BaseFragment<FragmentTop250movieBinding>(
    FragmentTop250movieBinding::inflate
) {
    companion object {
        fun newInstance() = Top250MovieFragmentWithAppState()
    }

    private val viewModel: Top250MovieViewModel by viewModel()
    private lateinit var movieAdapter: MovieCollectionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()

        viewModel.top250LiveData.observe(viewLifecycleOwner){ pagingData ->
            movieAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        }
    }

    private fun initRV() {
        movieAdapter = MovieCollectionAdapter()
        binding.RVTop250.init(MovieCollectionAdapter(), LinearLayoutManager.HORIZONTAL)
    }
}