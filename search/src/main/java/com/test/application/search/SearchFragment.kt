package com.test.application.search

import android.os.Bundle
import android.view.View
import com.test.application.KEY_QUERY
import com.test.application.core.utils.replaceFragment
import com.test.application.core.view.BaseFragment
import com.test.application.history.HistoryFragment
import com.test.application.search.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>(
    FragmentSearchBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleSearchToolbar()
        handleFABHistory()
    }

    private fun handleFABHistory() {
        binding.historyButton.setOnClickListener {
            childFragmentManager.replaceFragment(R.id.container,
                HistoryFragment())
        }
    }

    private fun handleSearchToolbar() {
        binding.searchView.setOnQueryTextListener(object
            : androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                openSearchResultFragment(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun openSearchResultFragment(query: String) {
        val searchResultFragment = SearchResultFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_QUERY, query)
            }
        }
        childFragmentManager.beginTransaction()
            .replace(R.id.container, searchResultFragment)
            .commit()
    }
}