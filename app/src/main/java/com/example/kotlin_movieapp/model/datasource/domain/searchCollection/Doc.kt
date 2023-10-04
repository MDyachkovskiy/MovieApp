package com.example.kotlin_movieapp.model.datasource.domain.searchCollection

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Doc(
    val description: String = "",
    val id: Int = 0,
    val name: String = "",
    val poster: String = "",
) : Parcelable