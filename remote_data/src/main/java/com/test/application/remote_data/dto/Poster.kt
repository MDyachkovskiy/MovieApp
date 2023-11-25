package com.test.application.remote_data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Poster(
    val previewUrl: String? = "",
    val url: String? = ""
) : Parcelable