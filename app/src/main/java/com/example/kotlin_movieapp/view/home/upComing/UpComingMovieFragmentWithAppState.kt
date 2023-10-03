package com.example.kotlin_movieapp.view.home.upComing

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_movieapp.databinding.FragmentUpcomingBinding
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.domain.collection.CollectionsResponse
import com.example.kotlin_movieapp.utils.init
import com.example.kotlin_movieapp.view.base.BaseFragmentWithAppState
import com.example.kotlin_movieapp.view.home.adapter.MovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class UpComingMovieFragmentWithAppState : BaseFragmentWithAppState<AppState, CollectionsResponse, FragmentUpcomingBinding>(
    FragmentUpcomingBinding::inflate
) {

    companion object {
        fun newInstance() = UpComingMovieFragmentWithAppState()
    }

    private val viewModel: UpComingMovieViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        viewModel.getUpComingCollection()
    }

    override fun setupData(data: CollectionsResponse) {
        initRV(data)
    }

    private fun initRV(data:CollectionsResponse) {
        binding.RVUpComing.init(MovieAdapter(data), LinearLayoutManager.HORIZONTAL)
    }
}