package com.test.application.local_data.di

import android.content.Context
import androidx.room.Room
import com.test.application.local_data.contacts.ContactsDao
import com.test.application.local_data.database.MovieAppDataBase
import com.test.application.local_data.favorites.FavoriteMovieDao
import com.test.application.local_data.history.HistoryDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(
    private val applicationContext: Context
) {
    @Singleton
    @Provides
    fun provideDatabase(): MovieAppDataBase {
        return Room.databaseBuilder(
            applicationContext,
            MovieAppDataBase::class.java,
            "MovieAppDataBase.db"
        ).build()
    }

    @Provides
    fun provideHistoryDao(database: MovieAppDataBase): HistoryDao {
        return database.historyDao()
    }

    @Provides
    fun provideFavoriteDao(database: MovieAppDataBase): FavoriteMovieDao {
        return database.favoriteDao()
    }

    @Provides
    fun provideContactsDao(database: MovieAppDataBase): ContactsDao {
        return database.contactsDao()
    }
}
