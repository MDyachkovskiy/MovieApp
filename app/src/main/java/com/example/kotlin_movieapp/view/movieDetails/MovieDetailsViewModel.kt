package com.example.kotlin_movieapp.view.movieDetails

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.domain.movieDetail.MovieDetailsResponse
import com.example.kotlin_movieapp.model.repository.favorites.FavoritesRepository
import com.example.kotlin_movieapp.model.repository.history.LocalRepository
import com.example.kotlin_movieapp.model.repository.movieDetails.DetailsRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val detailsRepository: DetailsRepository,
    private val historyRepository: LocalRepository,
    private val favoriteRepository: FavoritesRepository
) : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData() = liveData

    fun getMovieFromRemoteSource(movieId: Int?) {
        viewModelScope.launch {
            liveData.value = AppState.Loading
            liveData.value = detailsRepository.getMovieDetailsFromServer(movieId)
        }
    }

    fun saveMovieToDB (movieDTO: MovieDetailsResponse, date: Long) {
        historyRepository.saveEntity(movieDTO, date)
    }

    fun saveFavoriteMovieToDB (movieDTO: MovieDetailsResponse) {
        favoriteRepository.saveEntity(movieDTO)
    }

    fun deleteFavoriteMovieFromDB (movieDTO: MovieDetailsResponse) {
        favoriteRepository.deleteEntity(movieDTO)
    }

    fun addCommentToMovie (movieDTO: MovieDetailsResponse, text: Editable?) {
        historyRepository.addUserComment(movieDTO,text)
    }
}