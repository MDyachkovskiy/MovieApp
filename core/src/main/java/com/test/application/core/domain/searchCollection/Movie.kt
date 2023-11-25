package com.test.application.core.domain.searchCollection

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val description: String = "",
    val id: Int = 0,
    val name: String = "",
    val poster: String = "",
) : Parcelable