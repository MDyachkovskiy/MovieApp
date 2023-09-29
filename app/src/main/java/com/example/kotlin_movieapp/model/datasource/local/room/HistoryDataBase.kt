package com.example.kotlin_movieapp.model.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlin_movieapp.model.datasource.local.room.contacts.ContactsDao
import com.example.kotlin_movieapp.model.datasource.local.room.contacts.ContactsEntity
import com.example.kotlin_movieapp.model.datasource.local.room.favorites.FavoriteMovieDao
import com.example.kotlin_movieapp.model.datasource.local.room.favorites.FavoriteMovieEntity
import com.example.kotlin_movieapp.model.datasource.local.room.history.HistoryDao
import com.example.kotlin_movieapp.model.datasource.local.room.history.HistoryEntity

@Database(version = 1,
    exportSchema = false,
    entities = [
    HistoryEntity ::class,
    FavoriteMovieEntity ::class,
    ContactsEntity ::class
    ]
)
abstract class HistoryDataBase : RoomDatabase() {
    abstract fun historyDao() : HistoryDao
    abstract fun favoriteDao() : FavoriteMovieDao
    abstract fun contactsDao() : ContactsDao
}