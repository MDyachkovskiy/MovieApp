package com.example.kotlin_movieapp.view.home.top250Movie

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_movieapp.databinding.FragmentTop250movieBinding
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.Top250Response
import com.example.kotlin_movieapp.utils.init
import com.example.kotlin_movieapp.view.base.BaseFragment
import com.example.kotlin_movieapp.view.home.MovieAdapter

class Top250MovieFragment : BaseFragment<AppState, Top250Response, FragmentTop250movieBinding>(
    FragmentTop250movieBinding::inflate
) {

    companion object {
        fun newInstance() = Top250MovieFragment()
    }

    private val viewModel: Top250MovieViewModel by lazy {
        ViewModelProvider(this)[Top250MovieViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner) { appState ->
            renderData(appState)
        }

        viewModel.getTop250Collection()
    }

    override fun setupData(data: Top250Response) {
        initRV(data)
    }

    private fun initRV(data: Top250Response) {
        val movieList = data.top250Movies
        binding.RVTop250.init(MovieAdapter(movieList), LinearLayoutManager.HORIZONTAL)
    }
}