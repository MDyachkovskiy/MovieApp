package com.example.kotlin_movieapp.model.AppState

import com.example.kotlin_movieapp.model.datasource.remote.movieDetailsResponse.MovieDTO
import com.example.kotlin_movieapp.model.datasource.remote.personDetailsResponse.PersonDTO

sealed class DetailsState {

    object Loading : DetailsState()

    data class SuccessMovie(val movieDTO : MovieDTO) : DetailsState()

    data class Error(val error : Throwable) : DetailsState()

    data class SuccessPerson (val personDTO : PersonDTO) : DetailsState()
}