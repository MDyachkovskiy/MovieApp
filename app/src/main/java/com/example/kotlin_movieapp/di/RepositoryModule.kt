package com.example.kotlin_movieapp.di

import com.test.application.core.domain.collection.Movie
import com.test.application.core.interactor.HomeScreenInteractor
import com.test.application.core.repository.CollectionsRepository
import com.test.application.core.repository.ContactsRepository
import com.test.application.core.repository.FavoritesRepository
import com.test.application.core.repository.HistoryRepository
import com.test.application.core.repository.MovieDetailsRepository
import com.test.application.core.repository.PersonDetailsRepository
import com.test.application.core.repository.SearchRepository
import com.test.application.local_data.contacts.ContactsDao
import com.test.application.local_data.favorites.FavoriteMovieDao
import com.test.application.local_data.history.HistoryDao
import com.test.application.local_data.repository.ContactsRepositoryImpl
import com.test.application.local_data.repository.FavoritesRepositoryImpl
import com.test.application.local_data.repository.HistoryRepositoryImpl
import com.test.application.remote_data.api.KinopoiskService
import com.test.application.remote_data.interactor.HomeScreenInteractorImpl
import com.test.application.remote_data.repository.CollectionsRepositoryImpl
import com.test.application.remote_data.repository.MovieDetailsRepositoryImpl
import com.test.application.remote_data.repository.PersonDetailsRepositoryImpl
import com.test.application.remote_data.repository.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideCollectionsRepository(kinopoiskService: KinopoiskService): CollectionsRepository<Movie> {
        return CollectionsRepositoryImpl(kinopoiskService)
    }
    @Singleton
    @Provides
    fun provideMovieDetailsRepository(kinopoiskService: KinopoiskService): MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(kinopoiskService)
    }

    @Singleton
    @Provides
    fun providePersonDetailsRepository(kinopoiskService: KinopoiskService): PersonDetailsRepository {
        return PersonDetailsRepositoryImpl(kinopoiskService)
    }

    @Singleton
    @Provides
    fun provideSearchRepository(kinopoiskService: KinopoiskService): SearchRepository {
        return SearchRepositoryImpl(kinopoiskService)
    }

    @Singleton
    @Provides
    fun provideContactsRepository(contactsDao: ContactsDao): ContactsRepository {
        return ContactsRepositoryImpl(contactsDao)
    }

    @Singleton
    @Provides
    fun provideFavoritesRepository(favoritesDao: FavoriteMovieDao): FavoritesRepository {
        return FavoritesRepositoryImpl(favoritesDao)
    }

    @Singleton
    @Provides
    fun provideHistoryRepository(historyDao: HistoryDao): HistoryRepository {
        return HistoryRepositoryImpl(historyDao)
    }

    @Singleton
    @Provides
    fun provideHomeScreenInteractor(
        collectionsRepository: CollectionsRepository<Movie>
    ): HomeScreenInteractor {
        return HomeScreenInteractorImpl(collectionsRepository)
    }
}