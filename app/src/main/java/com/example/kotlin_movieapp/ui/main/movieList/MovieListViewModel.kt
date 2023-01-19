package com.example.kotlin_movieapp.ui.main.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movieapp.repository.RepositoryImpl
import com.example.kotlin_movieapp.ui.main.AppState

class MovieListViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: RepositoryImpl = RepositoryImpl(),
) : ViewModel() {

    fun getData(): LiveData<AppState> {
        return liveData
    }

    fun getMovie() {
        Thread {
            liveData.postValue(AppState.Loading)
            liveData.postValue(AppState.Success(repository.getMovieFromLocalStorage()))
        }.start()
    }

}

