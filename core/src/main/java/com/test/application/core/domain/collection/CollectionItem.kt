package com.test.application.core.domain.collection

import com.test.application.core.domain.movieDetail.Poster


data class CollectionItem(
    val kinopoiskId: Int? = 0,
    var poster: Poster = Poster(),
    val name: String? = "",
    val description: String? = ""
)
