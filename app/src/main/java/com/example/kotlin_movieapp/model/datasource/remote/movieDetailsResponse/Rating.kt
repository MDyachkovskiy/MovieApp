package com.example.kotlin_movieapp.model.datasource.remote.movieDetailsResponse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(
    val _id: String?,
    val imdb: Double?,
    val kp: Double?,
) : Parcelable