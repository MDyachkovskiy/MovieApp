package com.example.kotlin_movieapp.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.repository.search.SearchRepositoryImpl
import kotlinx.coroutines.launch


class SearchViewModel(
    private val repository: SearchRepositoryImpl
) : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()

    fun getData(): LiveData<AppState> {
        return liveData
    }

    fun getSearchCollection(name: String) {
        viewModelScope.launch {
            liveData.value = AppState.Loading
            liveData.value = repository.getSearchCollection(name)
        }
    }
}

