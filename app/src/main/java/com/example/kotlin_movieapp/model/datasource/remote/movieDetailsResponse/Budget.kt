package com.example.kotlin_movieapp.model.datasource.remote.movieDetailsResponse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Budget(
    val _id: String?,
    val currency: String?,
    val value: Int?
) : Parcelable