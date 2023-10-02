package com.example.kotlin_movieapp.view.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.example.kotlin_movieapp.R
import com.example.kotlin_movieapp.databinding.FragmentSearchBinding
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.domain.collection.CollectionsResponse
import com.example.kotlin_movieapp.utils.replaceFragment
import com.example.kotlin_movieapp.view.base.BaseFragment
import com.example.kotlin_movieapp.view.history.HistoryFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment<AppState, CollectionsResponse, FragmentSearchBinding>(
    FragmentSearchBinding::inflate
) {
    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModel()
    override fun setupData(data: CollectionsResponse) {
        childFragmentManager.replaceFragment(R.id.container, SearchResultFragment(data))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        binding.searchView.setOnQueryTextListener(object
            : androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getSearchCollection(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                childFragmentManager.replaceFragment(R.id.container, HistoryFragment.newInstance())
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}