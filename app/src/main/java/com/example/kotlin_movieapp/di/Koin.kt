package com.example.kotlin_movieapp.di

import com.example.kotlin_movieapp.model.datasource.remote.RemoteDataSource
import com.example.kotlin_movieapp.model.repository.collections.CollectionsRepository
import com.example.kotlin_movieapp.model.repository.collections.CollectionsRepositoryImpl
import com.example.kotlin_movieapp.view.home.top250Movie.Top250MovieViewModel
import com.example.kotlin_movieapp.view.home.topTvShows.TopTvShowsViewModel
import com.example.kotlin_movieapp.view.home.upComing.UpComingMovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<CollectionsRepository> { CollectionsRepositoryImpl(get()) }
    single { RemoteDataSource() }
    viewModel { Top250MovieViewModel(get()) }
    viewModel { TopTvShowsViewModel(get()) }
    viewModel { UpComingMovieViewModel(get()) }
}