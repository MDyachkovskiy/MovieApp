package com.example.kotlin_movieapp.model.datasource.local.room.contacts

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactsItem(
    var id: String = "",
    var name: String? = "",
    var phoneNumber: String? = ""
) : Parcelable
