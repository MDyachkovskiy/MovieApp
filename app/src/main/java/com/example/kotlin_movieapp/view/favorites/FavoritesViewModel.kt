package com.example.kotlin_movieapp.view.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.repository.favorites.FavoritesRepository
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