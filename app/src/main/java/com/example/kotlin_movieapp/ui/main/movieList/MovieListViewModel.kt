package com.example.kotlin_movieapp.ui.main.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movieapp.models.Top250Response
import com.example.kotlin_movieapp.repository.CollectionsRepository
import com.example.kotlin_movieapp.repository.CollectionsRepositoryImpl
import com.example.kotlin_movieapp.repository.RemoteDataSource
import com.example.kotlin_movieapp.ui.main.AppState
import retrofit2.Call
import retrofit2.Response

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class MovieListViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: CollectionsRepository = CollectionsRepositoryImpl(RemoteDataSource()),
) : ViewModel() {

    fun getData(): LiveData<AppState> {
        return liveData
    }

    private val callback = object : retrofit2.Callback<Top250Response> {
        override fun onResponse(
            call: Call<Top250Response>,
            response: Response<Top250Response>,
        ) {
            val serverResponse : Top250Response? = response.body()
            liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        private fun checkResponse(serverResponse : Top250Response) : AppState {
            return if (serverResponse == null) {
                AppState.Error(Throwable(CORRUPTED_DATA))
            } else {
                AppState.Success(serverResponse)
            }
        }

        override fun onFailure(call: Call<Top250Response>, t: Throwable) {
            liveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

    }

    fun getTop250MovieCollection() {
        liveData.value = AppState.Loading
        repository.getTop250CollectionFromServer(callback)
    }

}

