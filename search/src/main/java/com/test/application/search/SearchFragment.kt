package com.test.application.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.test.application.core.utils.replaceFragment
import com.test.application.core.view.BaseFragment
import com.test.application.search.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class SearchFragment : BaseFragment<FragmentSearchBinding>(
    FragmentSearchBinding::inflate
) {
    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by activityViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.searchResultLiveData.observe(viewLifecycleOwner) { _ ->
            if(childFragmentManager.findFragmentById(R.id.container) !is SearchResultFragment){
                childFragmentManager.replaceFragment(R.id.container, SearchResultFragment())
            }
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
                childFragmentManager.replaceFragment(R.id.container,
                    HistoryFragmentWithAppState.newInstance())
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}