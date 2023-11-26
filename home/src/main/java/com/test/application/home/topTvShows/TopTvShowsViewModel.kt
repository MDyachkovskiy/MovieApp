package com.test.application.home.topTvShows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.test.application.core.domain.collection.Movie
import com.test.application.core.interactor.HomeScreenInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TopTvShowsViewModel(
    interactor: HomeScreenInteractor
) : ViewModel() {

    private val _topTvShowsLiveData = MutableLiveData<PagingData<Movie>>()
    val topTvShowsLiveData: LiveData<PagingData<Movie>> get() = _topTvShowsLiveData

    private val myPagingDataFlow: Flow<PagingData<Movie>> = interactor
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

