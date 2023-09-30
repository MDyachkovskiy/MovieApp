package com.example.kotlin_movieapp.view.home.topTvShows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.repository.collections.CollectionsRepository
import com.example.kotlin_movieapp.utils.REQUEST_ERROR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopTvShowsViewModel(
    private val repository: CollectionsRepository
) : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()
    fun getData(): LiveData<AppState> {
        return liveData
    }

    fun getTvShowsCollection() {
        liveData.value = AppState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getTopTvShowsCollectionFromServer()
                liveData.postValue(response)
            } catch (e: Throwable) {
                liveData.postValue(AppState.Error(Throwable(e.message ?: REQUEST_ERROR)))
            }
        }
    }
}

