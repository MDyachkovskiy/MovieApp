package com.example.kotlin_movieapp.model.datasource.remote.movieDetailsResponse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val name: String?
) : Parcelable