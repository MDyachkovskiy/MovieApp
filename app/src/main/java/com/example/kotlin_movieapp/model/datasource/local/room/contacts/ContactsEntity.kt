package com.example.kotlin_movieapp.model.datasource.local.room.contacts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactsEntity (
    @PrimaryKey
    var id: String,
    var name: String? = "",
    var phoneNumber: String? = ""
)