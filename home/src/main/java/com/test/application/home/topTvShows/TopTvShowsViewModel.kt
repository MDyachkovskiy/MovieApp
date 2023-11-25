package com.test.application.home.topTvShows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kotlin_movieapp.model.datasource.domain.collection.Movie
import com.example.kotlin_movieapp.model.repository.collections.CollectionsRepository
import com.test.application.core.domain.collection.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TopTvShowsViewModel(
    repository: CollectionsRepository
) : ViewModel() {

    private val _topTvShowsLiveData = MutableLiveData<PagingData<Movie>>()
    val topTvShowsLiveData: LiveData<PagingData<Movie>> get() = _topTvShowsLiveData

    private val myPagingDataFlow: Flow<PagingData<Movie>> = repository
        .getTopTvShowsCollectionFromServer()
        .cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            myPagingDataFlow.collectLatest { pagingData ->
                _topTvShowsLiveData.value = pagingData
            }
        }
    }
}

