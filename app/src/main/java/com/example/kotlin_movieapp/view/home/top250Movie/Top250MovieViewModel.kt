package com.example.kotlin_movieapp.view.home.top250Movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.Top250Response
import com.example.kotlin_movieapp.model.repository.collections.CollectionsRepository
import com.example.kotlin_movieapp.model.repository.collections.CollectionsRepositoryImpl
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import com.example.kotlin_movieapp.utils.REQUEST_ERROR
import com.example.kotlin_movieapp.utils.SERVER_ERROR
import retrofit2.Call
import retrofit2.Response



class Top250MovieViewModel(
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
            val serverResponse: Top250Response? = response.body()
            liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }
        override fun onFailure(call: Call<Top250Response>, t: Throwable) {
            liveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }
    }

        private fun checkResponse(serverResponse : Top250Response) : AppState {
            return AppState.SuccessMovie(serverResponse)
        }

    fun getTop250Collection() {
        liveData.value = AppState.Loading
        repository.getTop250CollectionFromServer(callback)
    }

}

