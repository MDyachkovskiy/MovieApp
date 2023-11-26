package com.test.application.home.top250Movie

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.application.core.navigation.Navigator
import com.test.application.core.utils.init
import com.test.application.core.view.BaseFragment
import com.test.application.home.adapter.MovieCollectionAdapter
import com.test.application.home.databinding.FragmentTop250movieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class Top250MovieFragment : BaseFragment<FragmentTop250movieBinding>(
    FragmentTop250movieBinding::inflate
) {
    companion object {
        fun newInstance() = Top250MovieFragment()
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
        movieAdapter.listener = {
            (activity as? Navigator)?.navigateFromFavoritesToMovieDetails()
        }
        binding.RVTop250.init(movieAdapter, LinearLayoutManager.HORIZONTAL)
    }
}