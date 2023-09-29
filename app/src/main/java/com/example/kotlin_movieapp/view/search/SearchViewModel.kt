package com.example.kotlin_movieapp.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.SearchResponse
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import com.example.kotlin_movieapp.model.repository.search.SearchRepositoryImpl
import com.example.kotlin_movieapp.utils.REQUEST_ERROR
import com.example.kotlin_movieapp.utils.SERVER_ERROR
import retrofit2.Call
import retrofit2.Response


class SearchViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: SearchRepositoryImpl = SearchRepositoryImpl(RemoteDataSource()),
) : ViewModel() {

    fun getData(): LiveData<AppState> {
        return liveData
    }

    private val callback = object : retrofit2.Callback<SearchResponse> {
        override fun onResponse(
            call: Call<SearchResponse>,
            response: Response<SearchResponse>,
        ) {
            val serverResponse: SearchResponse? = response.body()
            liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }
        override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
            liveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }
    }

        private fun checkResponse(serverResponse : SearchResponse) : AppState {
            return AppState.Success(serverResponse)
        }

    fun getAdultLatinSearchCollection(rating: Int?, name: String) {
        liveData.value = AppState.Loading
        repository.getAdultLatinSearchResultFromServer(rating, name, callback)
    }

    fun getLatinSearchCollection(rating: Int?, name: String) {
        liveData.value = AppState.Loading
        repository.getLatinSearchResultFromServer(rating, name, callback)
    }

    fun getAdultCyrillicSearchCollection(rating: Int?, name: String) {
        liveData.value = AppState.Loading
        repository.getAdultCyrillicSearchResultFromServer(rating, name, callback)
    }

    fun getCyrillicSearchCollection(rating: Int?, name: String) {
        liveData.value = AppState.Loading
        repository.getCyrillicSearchResultFromServer(rating, name, callback)
    }
}

