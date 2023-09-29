package com.example.kotlin_movieapp.view.history

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_movieapp.databinding.FragmentHistoryBinding
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.local.room.history.HistoryMovieItem
import com.example.kotlin_movieapp.utils.init
import com.example.kotlin_movieapp.view.base.BaseFragment

class HistoryFragment : BaseFragment<AppState, List<HistoryMovieItem>, FragmentHistoryBinding>(
    FragmentHistoryBinding::inflate
) {

    private val viewModel: HistoryViewModel by lazy {
        ViewModelProvider(this)[HistoryViewModel::class.java]
    }

    companion object {
        fun newInstance() = HistoryFragment()
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

    override fun setupData(data: List<HistoryMovieItem>) {
        initRV(data)
    }

    private fun initRV(movieData: List<HistoryMovieItem>) {
        binding.historyRecyclerView.init(
            HistoryMovieAdapter(movieData),
            LinearLayoutManager.VERTICAL
        )
    }
}