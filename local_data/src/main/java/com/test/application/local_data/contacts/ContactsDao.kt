package com.test.application.local_data.contacts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ContactsDao {

    @Query("SELECT * FROM ContactsEntity")
    fun getAllContacts() : List<ContactsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(entity: ContactsEntity)

    @Delete
    fun delete (entity: ContactsEntity)
}