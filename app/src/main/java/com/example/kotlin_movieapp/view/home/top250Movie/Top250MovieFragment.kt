package com.example.kotlin_movieapp.view.home.top250Movie

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_movieapp.databinding.FragmentTop250movieBinding
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.domain.collection.CollectionsResponse
import com.example.kotlin_movieapp.utils.init
import com.example.kotlin_movieapp.view.base.BaseFragment
import com.example.kotlin_movieapp.view.home.MovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class Top250MovieFragment : BaseFragment<AppState, CollectionsResponse, FragmentTop250movieBinding>(
    FragmentTop250movieBinding::inflate
) {

    companion object {
        fun newInstance() = Top250MovieFragment()
    }

    private val viewModel: Top250MovieViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner) { appState ->
            renderData(appState)
        }

        viewModel.getTop250Collection()
    }

    override fun setupData(data: CollectionsResponse) {
        initRV(data)
    }

    private fun initRV(data: CollectionsResponse) {
        binding.RVTop250.init(MovieAdapter(data), LinearLayoutManager.HORIZONTAL)
    }
}