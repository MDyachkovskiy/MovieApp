package com.example.kotlin_movieapp.view.home.upComing

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_movieapp.databinding.FragmentUpcomingBinding
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.UpComingResponse
import com.example.kotlin_movieapp.utils.init
import com.example.kotlin_movieapp.view.base.BaseFragment
import com.example.kotlin_movieapp.view.home.MovieAdapter

class UpComingMovieFragment : BaseFragment<AppState, UpComingResponse, FragmentUpcomingBinding>(
    FragmentUpcomingBinding::inflate
) {

    companion object {
        fun newInstance() = UpComingMovieFragment()
    }

    private val viewModel: UpComingMovieViewModel by lazy {
        ViewModelProvider(this)[UpComingMovieViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        viewModel.getUpComingCollection()
    }

    override fun setupData(data: UpComingResponse) {
        initRV(data)
    }

    private fun initRV(data: UpComingResponse) {
        val movieList = data.UpComingMovies
        binding.RVUpComing.init(MovieAdapter(movieList), LinearLayoutManager.HORIZONTAL)
    }
}