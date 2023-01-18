package com.example.kotlin_movieapp.model.collectionResponse

import android.os.Parcelable
import com.example.kotlin_movieapp.model.movieDetailsResponse.Poster
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CollectionItem(
    val id: Int? = 0,
    var poster: Poster? = Poster(),
    val name: String? = ""
) : Parcelable
