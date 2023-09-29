package com.example.kotlin_movieapp.model.datasource.remote.personDetailsResponse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BirthPlace(
    val value: String? = ""
) : Parcelable