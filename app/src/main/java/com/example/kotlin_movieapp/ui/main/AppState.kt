package com.example.kotlin_movieapp.ui.main

import com.example.kotlin_movieapp.model.collectionResponse.Top250Response
import com.example.kotlin_movieapp.model.collectionResponse.TopTvShowsResponse

sealed class AppState {
    object Loading : AppState()

    data class SuccessMovie(val movieData : Top250Response) : AppState()

    data class SuccessTvShow(val movieData : TopTvShowsResponse) : AppState()

    data class Error(val error : Throwable) : AppState()
}