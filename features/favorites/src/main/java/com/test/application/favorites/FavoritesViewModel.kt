package com.test.application.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.application.core.repository.FavoritesRepository
import com.test.application.core.utils.AppState.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val favoritesLiveData: MutableLiveData<AppState> = MutableLiveData()
    fun getLiveData() = favoritesLiveData

    fun getAllFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesLiveData.postValue(AppState.Loading)
            val sortedList = favoritesRepository.getAllFavorites().sortedByDescending { favoriteMovie ->
                favoriteMovie.date
            }
            favoritesLiveData.postValue(AppState.Success(sortedList))
        }
    }

    fun deleteFavoriteMovie(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesRepository.deleteEntity(movieId)
        }
    }
}