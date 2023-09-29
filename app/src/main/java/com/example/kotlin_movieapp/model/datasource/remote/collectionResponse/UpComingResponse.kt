package com.example.kotlin_movieapp.model.datasource.remote.collectionResponse

import com.google.gson.annotations.SerializedName

data class UpComingResponse(
    @SerializedName("movies")
    val UpComingMovies: List<CollectionItem>
)