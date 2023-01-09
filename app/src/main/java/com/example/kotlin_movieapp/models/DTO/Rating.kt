package com.example.kotlin_movieapp.models.DTO

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rating(
    val _id: String?,
    val imdb: Double?,
    val kp: Double?,
) : Parcelable