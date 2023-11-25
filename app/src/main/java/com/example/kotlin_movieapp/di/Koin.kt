package com.example.kotlin_movieapp.di

import androidx.room.Room
import com.example.kotlin_movieapp.model.datasource.local.room.MovieAppDataBase
import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import com.test.application.remote_data.repository.CollectionsRepository
import com.test.application.core.repository.collections.CollectionsRepositoryImpl
import com.example.kotlin_movieapp.model.repository.contacts.ContactsRepository
import com.example.kotlin_movieapp.model.repository.contacts.ContactsRepositoryImpl
import com.test.application.remote_data.repository.FavoritesRepository
import com.test.application.core.repository.favorites.FavoritesRepositoryImpl
import com.example.kotlin_movieapp.model.repository.history.LocalRepository
import com.example.kotlin_movieapp.model.repository.history.LocalRepositoryImpl
import com.example.kotlin_movieapp.model.repository.movieDetails.DetailsRepository
import com.example.kotlin_movieapp.model.repository.movieDetails.DetailsRepositoryImpl
import com.example.kotlin_movieapp.model.repository.personDetails.PersonDetailsRepository
import com.example.kotlin_movieapp.model.repository.personDetails.PersonDetailsRepositoryImpl
import com.example.kotlin_movieapp.model.repository.search.SearchRepository
import com.example.kotlin_movieapp.model.repository.search.SearchRepositoryImpl
import com.example.kotlin_movieapp.view.contacts.ContactsViewModel
import com.test.application.favorites.FavoritesViewModel
import com.example.kotlin_movieapp.view.history.HistoryViewModel
import com.example.kotlin_movieapp.view.movieDetails.MovieDetailsViewModel
import com.example.kotlin_movieapp.view.personDetails.PersonDetailsViewModel
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.test.application.core.interactor.HomeScreenInteractor
import com.test.application.core.interactor.HomeScreenInteractorImpl
import com.test.application.core.utils.KINOPOISK_DOMAIN
import com.test.application.remote_data.api.KinopoiskService
import com.test.application.search.SearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    viewModel { com.test.application.home.top250Movie.Top250MovieViewModel(get()) }
    viewModel { com.test.application.home.topTvShows.TopTvShowsViewModel(get()) }
    viewModel { com.test.application.home.upComing.UpComingMovieViewModel(get()) }
    viewModel { ContactsViewModel(get()) }
    viewModel { FavoritesViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { MovieDetailsViewModel(get(), get(), get()) }
    viewModel { PersonDetailsViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(KINOPOISK_DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(get())
            .build()
    }

    single{
        get<Retrofit>().create(KinopoiskService::class.java)
    }
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
    single<CollectionsRepository> { CollectionsRepositoryImpl(get()) }
    single<ContactsRepository> { ContactsRepositoryImpl(get()) }
    single<FavoritesRepository> { FavoritesRepositoryImpl(get()) }
    single<LocalRepository> { LocalRepositoryImpl(get()) }
    single<DetailsRepository> { DetailsRepositoryImpl(get()) }
    single<PersonDetailsRepository> { PersonDetailsRepositoryImpl(get()) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }
}

val interactorModule = module {
    single<HomeScreenInteractor> { HomeScreenInteractorImpl(get()) }
}