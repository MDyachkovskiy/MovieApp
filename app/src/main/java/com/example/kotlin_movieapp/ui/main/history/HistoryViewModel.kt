package com.example.kotlin_movieapp.ui.main.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movieapp.ui.main.DetailsState

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class HistoryViewModel(
    private val historyLiveData: MutableLiveData<DetailsState> = MutableLiveData(),
) : ViewModel() {

    fun getLiveData() = historyLiveData

    fun getAllHistory() {

    }
}