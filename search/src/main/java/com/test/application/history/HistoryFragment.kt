package com.test.application.history

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.application.core.domain.history.HistoryMovieItem
import com.test.application.core.navigation.Navigator
import com.test.application.core.utils.AppState.AppState
import com.test.application.core.utils.KEY_BUNDLE_MOVIE
import com.test.application.core.view.BaseFragmentWithAppState
import com.test.application.search.databinding.FragmentHistoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class HistoryFragment : BaseFragmentWithAppState<AppState, List<HistoryMovieItem>, FragmentHistoryBinding>(
    FragmentHistoryBinding::inflate
) {

    private val viewModel: HistoryViewModel by viewModel()

    companion object {
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.getAllHistory()
    }

    override fun setupData(data: List<HistoryMovieItem>) {
        val recyclerView = binding.rvHistory
        val historyAdapter = HistoryMovieAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        historyAdapter.update(data)
        recyclerView.adapter = historyAdapter

        historyAdapter.listener = { movieId ->
            val bundle = bundleOf(KEY_BUNDLE_MOVIE to movieId)
            (activity as Navigator).navigateToMovieDetailsFragment(bundle)
        }
    }
}