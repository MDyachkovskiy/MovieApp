package com.example.kotlin_movieapp.view.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.repository.history.LocalRepositoryImpl

class HistoryViewModel(
    private val historyRepository: LocalRepositoryImpl
) : ViewModel() {

    private val historyLiveData: MutableLiveData<AppState> = MutableLiveData()
    fun getLiveData() = historyLiveData

    fun getAllHistory() {
        historyLiveData.postValue(AppState.Loading)
        historyLiveData.postValue(AppState.Success(historyRepository.getAllHistory()))
    }
}