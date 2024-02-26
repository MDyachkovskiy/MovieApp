package com.test.application.home.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.test.application.core.domain.collection.Movie
import com.test.application.core.interactor.HomeScreenInteractor
import com.test.application.home.util.MovieCollectionKey
import com.test.application.home.util.MovieType
import com.test.application.home.util.STREAMING_COLLECTION
import com.test.application.home.util.TOP_250_COLLECTION
import com.test.application.home.util.UPCOMING_COLLECTION
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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

    private val flowsCache = mutableMapOf<MovieCollectionKey, Flow<PagingData<Movie>>>()

    fun loadTop250Movies(movieType: MovieType) {
        val key = MovieCollectionKey(movieType, TOP_250_COLLECTION)
        val flow = flowsCache.getOrPut(key) {
            interactor.getTop250CollectionFromServer(movieType.type).cachedIn(viewModelScope)
        }
        viewModelScope.launch {
            flow.collectLatest { pagingData -> _top250LiveData.value = pagingData }
        }
    }

    fun loadTopTvShows(movieType: MovieType) {
        val key = MovieCollectionKey(movieType, STREAMING_COLLECTION)
        val flow = flowsCache.getOrPut(key) {
            interactor.getTopTvShowsCollectionFromServer(movieType.type).cachedIn(viewModelScope)
        }
        viewModelScope.launch {
            flow.collectLatest { pagingData ->  _topTvShowsLiveData.value = pagingData}
        }
    }

    fun loadUpcomingMovies(movieType: MovieType) {
        val key = MovieCollectionKey(movieType, UPCOMING_COLLECTION)
        val flow = flowsCache.getOrPut(key) {
            interactor.getUpComingCollectionFromServer(movieType.type).cachedIn(viewModelScope)

        }
        viewModelScope.launch {
            flow.collectLatest { pagingData ->
                _upComingLiveData.value = pagingData }
        }
    }
}