package com.test.application.core.domain.searchCollection

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchMovie(
    val id: Int = 0,
    val name: String = "",
    val poster: String = "",
    val description: String = "",
) : Parcelable