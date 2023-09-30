package com.example.kotlin_movieapp.view.home.topTvShows

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_movieapp.databinding.FragmentTvshowsBinding
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.domain.collection.CollectionsResponse
import com.example.kotlin_movieapp.utils.init
import com.example.kotlin_movieapp.view.base.BaseFragment
import com.example.kotlin_movieapp.view.home.MovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class TopTvShowsFragment : BaseFragment<AppState, CollectionsResponse, FragmentTvshowsBinding>(
    FragmentTvshowsBinding::inflate
) {

    companion object {
        fun newInstance() = TopTvShowsFragment()
    }

    private val viewModel: TopTvShowsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        viewModel.getTvShowsCollection()
    }


    override fun setupData(data: CollectionsResponse) {
        initRV(data)
    }

    private fun initRV(data: CollectionsResponse) {
        binding.RVTvShows.init(MovieAdapter(data), LinearLayoutManager.HORIZONTAL)
    }
}