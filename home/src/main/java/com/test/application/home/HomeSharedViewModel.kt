package com.test.application.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.test.application.core.domain.collection.Movie
import com.test.application.core.interactor.HomeScreenInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeSharedViewModel @Inject constructor(
    private val interactor: HomeScreenInteractor
) : ViewModel() {

    private val _top250LiveData = MutableLiveData<PagingData<Movie>>()
    val top250LiveData: LiveData<PagingData<Movie>> = _top250LiveData

    private val _topTvShowsLiveData = MutableLiveData<PagingData<Movie>>()
    val topTvShowsLiveData: LiveData<PagingData<Movie>> = _topTvShowsLiveData

    private val _upComingLiveData = MutableLiveData<PagingData<Movie>>()
    val upComingLiveData: LiveData<PagingData<Movie>> = _upComingLiveData

    init {
        viewModelScope.launch {
            interactor.getTop250CollectionFromServer().cachedIn(viewModelScope)
                .collectLatest { pagingData -> _top250LiveData.value = pagingData }

            interactor.getTopTvShowsCollectionFromServer().cachedIn(viewModelScope)
                .collectLatest { pagingData ->  _topTvShowsLiveData.value = pagingData}

            interactor.getUpComingCollectionFromServer().cachedIn(viewModelScope)
                .collectLatest { pagingData -> _upComingLiveData.value = pagingData }
        }
    }
}