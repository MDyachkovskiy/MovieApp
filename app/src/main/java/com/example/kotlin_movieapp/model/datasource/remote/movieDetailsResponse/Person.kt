package com.example.kotlin_movieapp.model.datasource.remote.movieDetailsResponse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val enName: String? = "",
    val enProfession: String? = "",
    val id: Int = 0,
    val name: String? = "",
    val photo: String? = ""
) : Parcelable