package com.test.application.core.domain.movieDetail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val description: String = "",
    val enName: String = "",
    val enProfession: String = "",
    val id: Int = 0,
    val name: String = "",
    val photo: String = "",
    val profession: String = ""
): Parcelable