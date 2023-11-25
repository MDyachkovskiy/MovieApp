package com.example.kotlin_movieapp.view.movieDetails

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.application.core.utils.AppState.AppState
import com.test.application.core.domain.movieDetail.MovieDetails
import com.test.application.remote_data.repository.FavoritesRepository
import com.example.kotlin_movieapp.model.repository.history.LocalRepository
import com.example.kotlin_movieapp.model.repository.movieDetails.DetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val detailsRepository: DetailsRepository,
    private val historyRepository: LocalRepository,
    private val favoriteRepository: com.test.application.remote_data.repository.FavoritesRepository
) : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    fun getLiveData() = liveData

    fun getMovieFromRemoteSource(movieId: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            liveData.postValue(AppState.Loading)
            liveData.postValue(detailsRepository.getMovieDetailsFromServer(movieId))
        }
    }

    fun saveMovieToDB (movieDTO: MovieDetails, date: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            historyRepository.saveEntity(movieDTO, date)
        }
    }

    fun saveFavoriteMovieToDB (movieDTO: MovieDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.saveEntity(movieDTO)
        }
    }

    fun deleteFavoriteMovieFromDB (movieDTO: MovieDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.deleteEntity(movieDTO)
        }
    }

    fun addCommentToMovie (movieDTO: MovieDetails, text: Editable?) {
        viewModelScope.launch(Dispatchers.IO) {
            historyRepository.addUserComment(movieDTO,text)
        }
    }

    fun checkFavoriteMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _isFavorite.postValue(favoriteRepository.checkExistenceInDB(id))
        }
    }
}