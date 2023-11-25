package com.test.application.home.top250Movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kotlin_movieapp.model.datasource.domain.collection.Movie
import com.example.kotlin_movieapp.model.repository.collections.CollectionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class Top250MovieViewModel(
    repository: CollectionsRepository
) : ViewModel() {

    private val _top250LiveData = MutableLiveData<PagingData<Movie>>()
    val top250LiveData: LiveData<PagingData<Movie>> get() = _top250LiveData

    private val myPagingDataFlow: Flow<PagingData<Movie>> = repository.getTop250CollectionFromServer()
        .cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            myPagingDataFlow.collectLatest { pagingData ->
                _top250LiveData.value = pagingData
            }
        }
    }
}

