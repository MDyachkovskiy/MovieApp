package com.example.kotlin_movieapp.model.datasource.remote.collectionResponse

import android.os.Parcelable
import com.example.kotlin_movieapp.model.datasource.domain.collection.CollectionItem
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResponse(
    @SerializedName("docs")
    val searchResults: List<CollectionItem>
) : Parcelable