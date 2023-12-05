package com.test.application.core.domain.favorites

data class FavoriteMovieItem (
    var kinopoiskId: Int = 0,
    var name: String? = "",
    var description: String? = "",
    var poster: String? = "",
    var date: Long = 0,
    var userNote: String? = "",
    var isFavorite: Boolean = false
)