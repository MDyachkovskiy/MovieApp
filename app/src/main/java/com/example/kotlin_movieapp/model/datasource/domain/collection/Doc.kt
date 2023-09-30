package com.example.kotlin_movieapp.model.datasource.domain.collection

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Doc(
    val id: Int = 0,
    val name: String = "",
    val poster: Poster = Poster()
): Parcelable