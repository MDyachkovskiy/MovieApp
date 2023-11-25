package com.test.application.home.topTvShows

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_movieapp.databinding.FragmentTvshowsBinding
import com.example.kotlin_movieapp.utils.init
import com.example.kotlin_movieapp.view.base.BaseFragment
import com.test.application.home.adapter.MovieCollectionAdapter
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
        binding.RVTvShows.init(movieAdapter, LinearLayoutManager.HORIZONTAL)
    }
}