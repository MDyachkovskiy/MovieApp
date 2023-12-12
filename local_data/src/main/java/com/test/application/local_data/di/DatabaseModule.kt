package com.test.application.local_data.di

import android.content.Context
import androidx.room.Room
import com.test.application.local_data.contacts.ContactsDao
import com.test.application.local_data.database.MovieAppDataBase
import com.test.application.local_data.favorites.FavoriteMovieDao
import com.test.application.local_data.history.HistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieAppDataBase {
        return Room.databaseBuilder(
            context,
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
