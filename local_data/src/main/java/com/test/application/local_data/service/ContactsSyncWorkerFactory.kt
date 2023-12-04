package com.test.application.local_data.service

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.test.application.local_data.contacts.ContactsGetter

class ContactsSyncWorkerFactory(
    private val contactsGetter: ContactsGetter
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when(workerClassName) {
            ContactsSyncWorker::class.java.name -> ContactsSyncWorker(
                appContext, workerParameters, contactsGetter)
            else -> null
        }
    }
}