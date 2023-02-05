package com.example.kotlin_movieapp.ui.main.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_movieapp.adapters.HistoryMovieAdapter
import com.example.kotlin_movieapp.databinding.FragmentHistoryBinding
import com.example.kotlin_movieapp.model.room.history.HistoryMovieItem
import com.example.kotlin_movieapp.ui.main.AppState
import com.example.kotlin_movieapp.ui.main.AppStateRenderer
import com.example.kotlin_movieapp.utils.init

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val dataRenderer by lazy {AppStateRenderer(binding)}

    private val viewModel: HistoryViewModel by lazy {
        ViewModelProvider(this)[HistoryViewModel::class.java]
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loadingLayout.visibility = View.VISIBLE

        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        Thread {
            viewModel.getAllHistory()
        }.start()
    }

    private fun renderData(appState : AppState) {

        dataRenderer.render(appState)

        when (appState) {
            is AppState.SuccessHistory -> {
                initRV(appState.movieData)
            }
            else -> {return}
        }
    }

    private fun initRV(movieData: List<HistoryMovieItem>) {
        binding.historyRecyclerView.init(
            HistoryMovieAdapter(movieData),
            LinearLayoutManager.VERTICAL
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}