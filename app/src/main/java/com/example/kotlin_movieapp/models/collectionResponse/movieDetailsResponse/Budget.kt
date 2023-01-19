package com.example.kotlin_movieapp.models.collectionResponse.movieDetailsResponse

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Budget(
    val _id: String?,
    val currency: String?,
    val value: Int?
) : Parcelable