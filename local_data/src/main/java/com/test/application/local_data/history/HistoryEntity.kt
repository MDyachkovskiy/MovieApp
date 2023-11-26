package com.test.application.local_data.history

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey
    var kinopoiskId: Int,
    var name: String? ="",
    var description: String? ="",
    var poster: String? = null,
    var date: Long = 0L,
    var userNote: String? = "",
)