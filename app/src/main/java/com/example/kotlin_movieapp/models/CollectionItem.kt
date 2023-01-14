package com.example.kotlin_movieapp.models

import android.os.Parcelable
import com.example.kotlin_movieapp.models.DTO.Poster
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CollectionItem(
    val id: Int? = 0,
    var poster: Poster?,
    val name: String = "",
) : Parcelable
