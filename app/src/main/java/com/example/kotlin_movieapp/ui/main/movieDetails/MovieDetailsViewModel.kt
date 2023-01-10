package com.example.kotlin_movieapp.ui.main.movieDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movieapp.models.DTO.MovieDTO
import com.example.kotlin_movieapp.repository.RepositoryImpl
import com.example.kotlin_movieapp.ui.main.AppState

class MovieDetailsViewModel(
    private val repository: RepositoryImpl = RepositoryImpl(),
) : ViewModel() {
    private val _movieDetail = MutableLiveData<MovieDTO>()
    val movieDetails: LiveData<MovieDTO> = _movieDetail

    fun loadMovie(movieId: Int) {
        if(_movieDetail.value != null) return
        try {
            _movieDetail.value = repository.getMovieFromServer(movieId)
        } catch (e: java.lang.Exception) {
            Log.e("MDT", "Ошибка прогрузки деталей фильма", e)
            e.printStackTrace()
        }

    }

}