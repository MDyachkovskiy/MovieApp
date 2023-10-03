package com.example.kotlin_movieapp.view.home.upComing

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_movieapp.databinding.FragmentUpcomingBinding
import com.example.kotlin_movieapp.utils.init
import com.example.kotlin_movieapp.view.base.BaseFragment
import com.example.kotlin_movieapp.view.home.adapter.MovieCollectionAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class UpComingMovieFragment : BaseFragment<FragmentUpcomingBinding>(
    FragmentUpcomingBinding::inflate
) {

    companion object {
        fun newInstance() = UpComingMovieFragment()
    }

    private val viewModel: UpComingMovieViewModel by viewModel()
    private lateinit var movieAdapter: MovieCollectionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        viewModel.upComingLiveData.observe(viewLifecycleOwner) { pagingData ->
            movieAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        }
    }

    private fun initRV() {
        movieAdapter = MovieCollectionAdapter()
        binding.RVUpComing.init(movieAdapter, LinearLayoutManager.HORIZONTAL)
    }
}