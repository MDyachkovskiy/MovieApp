package com.example.kotlin_movieapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData()) :
    ViewModel() {

    fun getData(): LiveData<AppState> {
        return liveData
    }

    fun getMovie(){
        Thread {
            liveData.postValue(AppState.Loading)
            sleep(2000L)
            liveData.postValue(AppState.Success(Any()))
        }.start()
    }

}