package com.test.application.core.domain.collection

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int = 0,
    val name: String = "",
    val poster: Poster = Poster(),
    val description: String? = ""
): Parcelable