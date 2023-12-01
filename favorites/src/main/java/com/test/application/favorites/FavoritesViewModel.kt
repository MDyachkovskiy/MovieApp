package com.test.application.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.application.core.repository.FavoritesRepository
import com.test.application.core.utils.AppState.AppState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val favoritesLiveData: MutableLiveData<AppState> = MutableLiveData()
    fun getLiveData() = favoritesLiveData

    fun getAllFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesLiveData.postValue(AppState.Loading)
            favoritesLiveData.postValue(AppState.Success(favoritesRepository.getAllFavorites()))
        }
    }
}