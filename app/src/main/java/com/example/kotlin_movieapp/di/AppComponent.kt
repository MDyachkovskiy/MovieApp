package com.example.kotlin_movieapp.di

import android.app.Application
import com.example.kotlin_movieapp.app.App
import com.test.application.local_data.di.DatabaseModule
import com.test.application.remote_data.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class, NetworkModule::class, RepositoryModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun databaseModule(databaseModule: DatabaseModule): Builder
        fun build(): AppComponent
    }
    fun inject(application: App)
}