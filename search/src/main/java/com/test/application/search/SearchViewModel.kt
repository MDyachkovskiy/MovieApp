package com.test.application.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kotlin_movieapp.model.datasource.domain.searchCollection.Movie
import com.example.kotlin_movieapp.model.repository.search.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class SearchViewModel(
    private val repository: SearchRepository
) : ViewModel() {

    private val _searchResultLiveData = MutableLiveData<PagingData<Movie>>()
    val searchResultLiveData: LiveData<PagingData<Movie>> get() = _searchResultLiveData

    fun getSearchCollection(name: String) {
        val myPagingDataFlow: Flow<PagingData<Movie>> = repository.getSearchCollection(name)
            .cachedIn(viewModelScope)

        viewModelScope.launch {
            myPagingDataFlow.collectLatest {pagingData ->
                _searchResultLiveData.value = pagingData
            }
        }
    }
}

