package com.test.application.remote_data.dto.collection

import com.google.gson.annotations.SerializedName
data class CollectionItem(
    @SerializedName("id")
    val kinopoiskId: Int? = 0,
    var poster: PosterDTO = PosterDTO(),
    val name: String? = "",
    val description: String? = ""
)
