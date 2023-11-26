package com.test.application.local_data.contacts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactsEntity (
    @PrimaryKey
    var id: String,
    var name: String? = "",
    var phoneNumber: String? = ""
)