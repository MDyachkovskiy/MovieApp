package com.test.application.history

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_movieapp.databinding.FragmentHistoryBinding
import com.test.application.core.utils.AppState.AppState
import com.test.application.local_data.history.HistoryMovieItem
import com.test.application.core.utils.init
import org.koin.androidx.viewmodel.ext.android.viewModel


class HistoryFragmentWithAppState : com.test.application.core.view.BaseFragmentWithAppState<AppState, List<com.test.application.local_data.history.HistoryMovieItem>, FragmentHistoryBinding>(
    FragmentHistoryBinding::inflate
) {

    private val viewModel: HistoryViewModel by viewModel()

    companion object {
        fun newInstance() = HistoryFragmentWithAppState()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        Thread {
            viewModel.getAllHistory()
        }.start()
    }

    override fun setupData(data: List<com.test.application.local_data.history.HistoryMovieItem>) {
        initRV(data)
    }

    private fun initRV(movieData: List<com.test.application.local_data.history.HistoryMovieItem>) {
        binding.historyRecyclerView.init(
            HistoryMovieAdapter(movieData),
            LinearLayoutManager.VERTICAL
        )
    }
}