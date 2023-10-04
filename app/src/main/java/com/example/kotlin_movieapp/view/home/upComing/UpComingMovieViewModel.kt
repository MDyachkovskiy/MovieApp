package com.example.kotlin_movieapp.view.home.upComing

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

class UpComingMovieViewModel(
    repository: CollectionsRepository
) : ViewModel() {

    private val _upComingLiveData = MutableLiveData<PagingData<Doc>>()
    val upComingLiveData: LiveData<PagingData<Doc>> get() = _upComingLiveData

    private val myPagingDataFlow: Flow<PagingData<Doc>> = repository
        .getUpComingCollectionFromServer()
        .cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            myPagingDataFlow.collectLatest { pagingData ->
                _upComingLiveData.value = pagingData
            }
        }
    }
}

