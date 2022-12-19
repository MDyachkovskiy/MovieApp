package com.example.kotlin_movieapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movieapp.repository.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(private val liveData : MutableLiveData<AppState> = MutableLiveData(),
                    private val repository : RepositoryImpl = RepositoryImpl()
                    ) :
    ViewModel() {

    fun getData() : LiveData<AppState> {
        return liveData
    }

    fun getMovie() {
        Thread {
            liveData.postValue(AppState.Loading)
            sleep(2000L)
            if ((0..10).random() > 5)
                liveData.postValue(AppState.Success(repository.getMovieFromServer()))
            else
                liveData.postValue(AppState.Error(IllegalAccessException()))
        }.start()
    }

}

