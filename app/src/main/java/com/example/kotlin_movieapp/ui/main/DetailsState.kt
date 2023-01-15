package com.example.kotlin_movieapp.ui.main

import com.example.kotlin_movieapp.models.collectionResponse.movieDetailsResponse.MovieDTO

sealed class DetailsState {
    object Loading : DetailsState()
    data class Success(val movieDTO : MovieDTO) : DetailsState()
    data class Error(val error : Throwable) : DetailsState()
}