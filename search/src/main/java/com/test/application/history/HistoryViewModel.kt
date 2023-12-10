package com.test.application.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.application.core.utils.AppState.AppState
import com.test.application.core.repository.HistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryViewModel(
    private val historyRepository: HistoryRepository
) : ViewModel() {

    private val historyLiveData: MutableLiveData<AppState> = MutableLiveData()
    fun getLiveData() = historyLiveData

    fun getAllHistory() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                historyLiveData.postValue(AppState.Loading)
                historyLiveData.postValue(AppState.Success(historyRepository.getAllHistory()))
            }
        }
    }
}