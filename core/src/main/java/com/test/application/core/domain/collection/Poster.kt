package com.test.application.core.domain.collection

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Poster(
    val previewUrl: String = "",
    val url: String = ""
): Parcelable