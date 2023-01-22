package com.example.kotlin_movieapp.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey (autoGenerate = true) val id: Long = 0,
    val name: String? ="",
    val description: String? ="",
    val date: Long = 0,
    val userNotes: String? = "",
)