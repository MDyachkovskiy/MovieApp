package com.test.application.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                childFragmentManager.replaceFragment(R.id.container,
                    HistoryFragment.newInstance())
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}