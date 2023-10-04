package com.example.kotlin_movieapp.app

import android.app.Application
import com.example.kotlin_movieapp.di.appModule
import com.example.kotlin_movieapp.di.databaseModule
import com.example.kotlin_movieapp.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, databaseModule, repositoryModule))
        }
    }
}