package com.test.application.home.top250Movie

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


class Top250MovieViewModel(
    interactor: HomeScreenInteractor
) : ViewModel() {

    private val _top250LiveData = MutableLiveData<PagingData<Movie>>()
    val top250LiveData: LiveData<PagingData<Movie>> get() = _top250LiveData

    private val myPagingDataFlow: Flow<PagingData<Movie>> = interactor
        .getTop250CollectionFromServer()
        .cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            myPagingDataFlow.collectLatest { pagingData ->
                _top250LiveData.value = pagingData
            }
        }
    }
}

