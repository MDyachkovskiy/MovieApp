package com.example.kotlin_movieapp.models.collectionResponse

import android.os.Parcelable
import com.example.kotlin_movieapp.models.collectionResponse.movieDetailsResponse.Poster
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CollectionItem(
    val id: Int? = 0,
    var poster: Poster? = Poster(),
    val name: String? = "",
    val top250: Int? = 0
) : Parcelable
