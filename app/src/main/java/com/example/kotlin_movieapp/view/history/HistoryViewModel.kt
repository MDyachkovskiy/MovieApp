package com.example.kotlin_movieapp.view.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.repository.history.LocalRepository
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val historyRepository: LocalRepository
) : ViewModel() {

    private val historyLiveData: MutableLiveData<AppState> = MutableLiveData()
    fun getLiveData() = historyLiveData

    fun getAllHistory() {
        viewModelScope.launch {
            historyLiveData.postValue(AppState.Loading)
            historyLiveData.postValue(AppState.Success(historyRepository.getAllHistory()))
        }
    }
}