package com.test.application.local_data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.application.local_data.contacts.ContactsDao
import com.test.application.local_data.contacts.ContactsEntity
import com.test.application.local_data.favorites.FavoriteMovieDao
import com.test.application.local_data.favorites.FavoriteMovieEntity
import com.test.application.local_data.history.HistoryDao
import com.test.application.local_data.history.HistoryEntity

@Database(version = 1,
    exportSchema = false,
    entities = [
    HistoryEntity ::class,
    FavoriteMovieEntity ::class,
    ContactsEntity ::class
    ]
)
abstract class MovieAppDataBase : RoomDatabase() {
    abstract fun historyDao() : HistoryDao
    abstract fun favoriteDao() : FavoriteMovieDao
    abstract fun contactsDao() : ContactsDao
}