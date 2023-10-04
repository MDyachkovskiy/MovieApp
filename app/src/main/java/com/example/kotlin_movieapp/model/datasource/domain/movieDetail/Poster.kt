package com.example.kotlin_movieapp.model.datasource.domain.movieDetail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Poster(
    val previewUrl: String? = "",
    val url: String? = ""
) : Parcelable