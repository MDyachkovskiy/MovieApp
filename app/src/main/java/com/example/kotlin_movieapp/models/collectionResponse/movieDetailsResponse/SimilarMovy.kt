package com.example.kotlin_movieapp.models.collectionResponse.movieDetailsResponse

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SimilarMovy(
    val _id: String?,
    val alternativeName: String?,
    val enName: String?,
    val id: Int?,
    val name: String?,
    val poster: Poster?
) : Parcelable