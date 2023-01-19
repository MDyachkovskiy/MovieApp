package com.example.kotlin_movieapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id : Int = 0,
    var image : Int = 0,
    val name : String = "",
    val description : String = ""
) : Parcelable

fun getTopMovies() : List<Movie> = listOf(
        Movie(id =380),
        Movie(id =386),
        Movie(id =372),
        Movie(id =397),
        Movie(id =341),
        Movie(id =354),
        Movie(id =251733)
    )
