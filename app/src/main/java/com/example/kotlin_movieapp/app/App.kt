package com.example.kotlin_movieapp.app

import android.app.Application
import androidx.work.Configuration
import androidx.work.DelegatingWorkerFactory
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.kotlin_movieapp.di.AppComponent
import com.example.kotlin_movieapp.di.workerModule
import com.test.application.local_data.di.DatabaseModule
import com.test.application.local_data.service.ContactsSyncWorker
import com.test.application.local_data.service.ContactsSyncWorkerFactory
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.concurrent.TimeUnit

class App : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
            appComponent = DaggerAppComponent.builder()
                .application(this)
                .databaseModule(DatabaseModule(this))
                .build()
        startKoin {
            androidContext(this@App)
            modules(listOf(workerModule))
        }
        setupWorkManager()
    }

    private fun setupWorkManager() {
        val workerFactory = DelegatingWorkerFactory().apply{
            addFactory(get<ContactsSyncWorkerFactory>())
        }

        WorkManager.initialize(
            this,
            Configuration.Builder().setWorkerFactory(workerFactory).build()
        )

        val workRequest = PeriodicWorkRequestBuilder<ContactsSyncWorker>(1, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "contacts_sync",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}