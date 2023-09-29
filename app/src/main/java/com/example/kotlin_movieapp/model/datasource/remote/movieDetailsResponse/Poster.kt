package com.example.kotlin_movieapp.model.datasource.remote.movieDetailsResponse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Poster(
    val _id: String? = "",
    val previewUrl: String? = "",
    val url: String? = ""
) : Parcelable