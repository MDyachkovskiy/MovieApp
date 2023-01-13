package com.example.kotlin_movieapp.ui.main.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movieapp.models.DTO.MovieDTO
import com.example.kotlin_movieapp.repository.DetailsRepository
import com.example.kotlin_movieapp.repository.DetailsRepositoryImpl
import com.example.kotlin_movieapp.repository.RemoteDataSource
import com.example.kotlin_movieapp.ui.main.DetailsState
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Response
import java.io.IOException

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class MovieDetailsViewModel(
    private val liveData: MutableLiveData<DetailsState> = MutableLiveData(),
    private val repository: DetailsRepository = DetailsRepositoryImpl(RemoteDataSource()),
) : ViewModel() {

    private val callback = object : okhttp3.Callback {
        override fun onFailure(call: Call, e: IOException) {
            liveData.postValue(DetailsState.Error(Throwable(e.message ?: REQUEST_ERROR)))
        }

        override fun onResponse(call: Call, response: Response) {
            val serverResponse : String? = response.body()?.string()
            liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    DetailsState.Error(Throwable(SERVER_ERROR))
                }
            )
        }
    }

    private fun checkResponse (serverResponse: String) : DetailsState{
        val movieDTO : MovieDTO =
            Gson().fromJson(serverResponse, MovieDTO::class.java)

        return if (movieDTO == null) {
            DetailsState.Error(Throwable(CORRUPTED_DATA))
        } else {
            DetailsState.Success(movieDTO)
        }
    }

    fun getLiveData() = liveData

    fun getMovieFromRemoteSource(requestLink: Int) {
        liveData.value = DetailsState.Loading
        repository.getMovieDetails(requestLink, callback)
    }
}