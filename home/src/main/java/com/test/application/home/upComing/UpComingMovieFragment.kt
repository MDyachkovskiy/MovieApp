package com.test.application.home.upComing

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.application.core.navigation.Navigator
import com.test.application.core.utils.init
import com.test.application.core.view.BaseFragment
import com.test.application.home.adapter.MovieCollectionAdapter
import com.test.application.home.databinding.FragmentUpcomingBinding
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
        movieAdapter.listener = {
            (activity as? Navigator)?.navigateFromFavoritesToMovieDetails()
        }
        binding.RVUpComing.init(movieAdapter, LinearLayoutManager.HORIZONTAL)
    }
}