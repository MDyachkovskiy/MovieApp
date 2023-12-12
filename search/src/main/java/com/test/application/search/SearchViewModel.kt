package com.test.application.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.test.application.core.domain.searchCollection.SearchMovie
import com.test.application.core.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    private val _searchResultLiveData = MutableLiveData<PagingData<SearchMovie>>()
    val searchResultLiveData: LiveData<PagingData<SearchMovie>> get() = _searchResultLiveData

    fun getSearchCollection(name: String) {
        val myPagingDataFlow: Flow<PagingData<SearchMovie>> = repository.getSearchCollection(name)
            .cachedIn(viewModelScope)

        viewModelScope.launch {
            myPagingDataFlow.collectLatest {pagingData ->
                _searchResultLiveData.value = pagingData
            }
        }
    }
}
