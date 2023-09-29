package com.example.kotlin_movieapp.model.AppState

import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.SearchResponse
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.Top250Response
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.TopTvShowsResponse
import com.example.kotlin_movieapp.model.datasource.remote.collectionResponse.UpComingResponse
import com.example.kotlin_movieapp.model.datasource.local.room.contacts.ContactsItem
import com.example.kotlin_movieapp.model.datasource.local.room.favorites.FavoriteMovieItem
import com.example.kotlin_movieapp.model.datasource.local.room.history.HistoryMovieItem

sealed class AppState {
    object Loading : AppState()

    data class SuccessMovie(val movieData : Top250Response) : AppState()

    data class SuccessTvShow(val movieData : TopTvShowsResponse) : AppState()

    data class SuccessUpComing(val movieData : UpComingResponse) : AppState()

    data class SuccessSearch (val movieData: SearchResponse) : AppState()

    data class SuccessHistory (val movieData: List<HistoryMovieItem>) : AppState()

    data class SuccessFavorites (val movieData: List<FavoriteMovieItem>) : AppState()

    data class SuccessContacts (val contactsData: List<ContactsItem>) : AppState()

    data class Error(val error : Throwable) : AppState()
}