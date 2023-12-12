package com.example.kotlin_movieapp.app

import android.app.Application
import androidx.work.Configuration
import androidx.work.DelegatingWorkerFactory
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.test.application.local_data.service.ContactsSyncWorker
import com.test.application.local_data.service.ContactsSyncWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var contactsSyncWorkerFactory: ContactsSyncWorkerFactory

    override fun onCreate() {
        super.onCreate()
        setupWorkManager()
    }

    private fun setupWorkManager() {
        val workerFactory = DelegatingWorkerFactory().apply{
            addFactory(contactsSyncWorkerFactory)
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