package com.example.kotlin_movieapp.view.home.top250Movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kotlin_movieapp.model.datasource.domain.collection.Doc
import com.example.kotlin_movieapp.model.repository.collections.CollectionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class Top250MovieViewModel(
    repository: CollectionsRepository
) : ViewModel() {

    private val _top250LiveData = MutableLiveData<PagingData<Doc>>()
    val top250LiveData: LiveData<PagingData<Doc>> get() = _top250LiveData

    val myPagingDataFlow: Flow<PagingData<Doc>> = repository.getTop250CollectionFromServer()
        .cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            myPagingDataFlow.collectLatest { pagingData ->
                _top250LiveData.value = pagingData
            }
        }
    }
}

