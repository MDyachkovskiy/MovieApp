package com.example.kotlin_movieapp.view.movieDetails

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movieapp.app.App
import com.example.kotlin_movieapp.model.AppState.DetailsState
import com.example.kotlin_movieapp.model.datasource.remote.movieDetailsResponse.MovieDTO
import com.example.kotlin_movieapp.model.repository.movieDetails.DetailsRepository
import com.example.kotlin_movieapp.model.repository.movieDetails.DetailsRepositoryImpl
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import com.example.kotlin_movieapp.model.repository.favorites.FavoritesRepositoryImpl
import com.example.kotlin_movieapp.model.repository.history.LocalRepositoryImpl
import com.example.kotlin_movieapp.utils.REQUEST_ERROR
import com.example.kotlin_movieapp.utils.SERVER_ERROR

class MovieDetailsViewModel(
    private val liveData: MutableLiveData<DetailsState> = MutableLiveData(),
    private val detailsRepository: DetailsRepository = DetailsRepositoryImpl(RemoteDataSource()),
    private val historyRepository: LocalRepositoryImpl = LocalRepositoryImpl(App.getHistoryDao()),
    private val favoriteRepository: FavoritesRepositoryImpl = FavoritesRepositoryImpl(App.getFavoriteDao())
) : ViewModel() {

    private val callback = object : retrofit2.Callback<MovieDTO> {

        override fun onFailure(call: retrofit2.Call<MovieDTO>, t: Throwable) {
            liveData.postValue(DetailsState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

        override fun onResponse(
            call: retrofit2.Call<MovieDTO>,
            response: retrofit2.Response<MovieDTO>,
        ) {
            val serverResponse : MovieDTO? = response.body()
            liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    DetailsState.Error(Throwable(SERVER_ERROR))
                }
            )
        }
    }

    private fun checkResponse (serverResponse: MovieDTO) : DetailsState {

        return DetailsState.SuccessMovie(serverResponse)
    }

    fun getLiveData() = liveData

    fun getMovieFromRemoteSource(movieId: Int?) {
        liveData.value = DetailsState.Loading
        detailsRepository.getMovieDetailsFromServer(movieId, callback)
    }

    fun saveMovieToDB (movieDTO: MovieDTO, date: Long) {
        historyRepository.saveEntity(movieDTO, date)
    }

    fun saveFavoriteMovieToDB (movieDTO: MovieDTO) {
        favoriteRepository.saveEntity(movieDTO)
    }

    fun deleteFavoriteMovieFromDB (movieDTO: MovieDTO) {
        favoriteRepository.deleteEntity(movieDTO)
    }

    fun addCommentToMovie (movieDTO: MovieDTO, text: Editable?) {
        historyRepository.addUserComment(movieDTO,text)
    }
}