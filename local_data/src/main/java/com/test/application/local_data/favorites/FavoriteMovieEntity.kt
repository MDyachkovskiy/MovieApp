package com.test.application.local_data.favorites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteMovieEntity(
    @PrimaryKey
    var kinopoiskId: Int,
    var name: String? ="",
    var description: String? ="",
    var poster: String? = null,
    var date: Long = 0L,
    var userNote: String? = "",
    var isFavorite: Boolean = false
)