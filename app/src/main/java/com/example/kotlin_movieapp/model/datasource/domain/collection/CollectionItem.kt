package com.example.kotlin_movieapp.model.datasource.domain.collection

import android.os.Parcelable
import com.example.kotlin_movieapp.model.datasource.domain.movieDetail.Poster

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class CollectionItem(
    @SerializedName("id")
    val kinopoiskId: Int? = 0,
    var poster: Poster = Poster(),
    val name: String? = "",
    val description: String? = ""
) : Parcelable