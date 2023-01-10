package com.example.kotlin_movieapp.ui.main.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movieapp.repository.RepositoryImpl
import com.example.kotlin_movieapp.ui.main.AppState

class MovieDetailsViewModel(
    private val _liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: RepositoryImpl = RepositoryImpl(),
) : ViewModel() {
    val liveData: LiveData<AppState> = _liveData


    fun loadMovie(movieId: Int) {
        Thread {
            _liveData.postValue(AppState.Loading)
            _liveData.postValue(AppState.SuccessMovieDetails(repository.getMovieFromServer(movieId)))
        }.start()


    }

}