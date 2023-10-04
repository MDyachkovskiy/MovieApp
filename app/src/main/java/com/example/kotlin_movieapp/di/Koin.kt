package com.example.kotlin_movieapp.di

import androidx.room.Room
import com.example.kotlin_movieapp.model.datasource.local.room.MovieAppDataBase
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import com.example.kotlin_movieapp.model.repository.collections.CollectionsRepository
import com.example.kotlin_movieapp.model.repository.collections.CollectionsRepositoryImpl
import com.example.kotlin_movieapp.model.repository.contacts.ContactsRepository
import com.example.kotlin_movieapp.model.repository.contacts.ContactsRepositoryImpl
import com.example.kotlin_movieapp.model.repository.favorites.FavoritesRepository
import com.example.kotlin_movieapp.model.repository.favorites.FavoritesRepositoryImpl
import com.example.kotlin_movieapp.model.repository.history.LocalRepository
import com.example.kotlin_movieapp.model.repository.history.LocalRepositoryImpl
import com.example.kotlin_movieapp.model.repository.movieDetails.DetailsRepository
import com.example.kotlin_movieapp.model.repository.movieDetails.DetailsRepositoryImpl
import com.example.kotlin_movieapp.model.repository.personDetails.PersonDetailsRepository
import com.example.kotlin_movieapp.model.repository.personDetails.PersonDetailsRepositoryImpl
import com.example.kotlin_movieapp.model.repository.search.SearchRepository
import com.example.kotlin_movieapp.model.repository.search.SearchRepositoryImpl
import com.example.kotlin_movieapp.view.contacts.ContactsViewModel
import com.example.kotlin_movieapp.view.favorites.FavoritesViewModel
import com.example.kotlin_movieapp.view.history.HistoryViewModel
import com.example.kotlin_movieapp.view.home.top250Movie.Top250MovieViewModel
import com.example.kotlin_movieapp.view.home.topTvShows.TopTvShowsViewModel
import com.example.kotlin_movieapp.view.home.upComing.UpComingMovieViewModel
import com.example.kotlin_movieapp.view.movieDetails.MovieDetailsViewModel
import com.example.kotlin_movieapp.view.personDetails.PersonDetailsViewModel
import com.example.kotlin_movieapp.view.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<CollectionsRepository> { CollectionsRepositoryImpl(get()) }
    single { RemoteDataSource() }
    viewModel { Top250MovieViewModel(get()) }
    viewModel { TopTvShowsViewModel(get()) }
    viewModel { UpComingMovieViewModel(get()) }
    viewModel { ContactsViewModel(get()) }
    viewModel { FavoritesViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { MovieDetailsViewModel(get(), get(), get()) }
    viewModel { PersonDetailsViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            MovieAppDataBase::class.java,
            "MovieAppDataBase.db"
        ).build()
    }
    factory { get<MovieAppDataBase>().historyDao() }
    factory { get<MovieAppDataBase>().favoriteDao() }
    factory { get<MovieAppDataBase>().contactsDao() }
}

val repositoryModule = module {
    single<ContactsRepository> { ContactsRepositoryImpl(get()) }
    single<FavoritesRepository> { FavoritesRepositoryImpl(get()) }
    single<LocalRepository> { LocalRepositoryImpl(get()) }
    single<DetailsRepository> { DetailsRepositoryImpl(get()) }
    single<PersonDetailsRepository> { PersonDetailsRepositoryImpl(get()) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }
}