package com.example.kotlin_movieapp.models.collectionResponse.movieDetailsResponse

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(
    val name: String?
) : Parcelable