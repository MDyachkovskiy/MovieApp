package com.example.kotlin_movieapp.view.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.repository.favorites.FavoritesRepositoryImpl

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepositoryImpl
) : ViewModel() {

    private val favoritesLiveData: MutableLiveData<AppState> = MutableLiveData()
    fun getLiveData() = favoritesLiveData

    fun getAllFavorites() {
        favoritesLiveData.postValue(AppState.Loading)
        favoritesLiveData.postValue(AppState.Success(favoritesRepository.getAllFavorites()))
    }
}