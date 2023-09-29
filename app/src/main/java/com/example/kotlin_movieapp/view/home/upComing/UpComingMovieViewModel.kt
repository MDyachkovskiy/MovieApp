package com.example.kotlin_movieapp.view.home.upComing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.UpComingResponse
import com.example.kotlin_movieapp.model.repository.collections.CollectionsRepository
import com.example.kotlin_movieapp.model.repository.collections.CollectionsRepositoryImpl
import com.example.kotlin_movieapp.utils.REQUEST_ERROR
import com.example.kotlin_movieapp.utils.SERVER_ERROR
import retrofit2.Call
import retrofit2.Response

class UpComingMovieViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: CollectionsRepository = CollectionsRepositoryImpl(RemoteDataSource()),
) : ViewModel() {

    fun getData(): LiveData<AppState> {
        return liveData
    }

    private val callback = object : retrofit2.Callback<UpComingResponse> {
        override fun onResponse(
            call: Call<UpComingResponse>,
            response: Response<UpComingResponse>,
        ) {
            val serverResponse: UpComingResponse? = response.body()
            liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }
        override fun onFailure(call: Call<UpComingResponse>, t: Throwable) {
            liveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }
    }

        private fun checkResponse(serverResponse : UpComingResponse) : AppState {
            return AppState.SuccessUpComing(serverResponse)
        }

    fun getUpComingCollection() {
        liveData.value = AppState.Loading
        repository.getUpComingCollectionFromServer(callback)
    }

}

