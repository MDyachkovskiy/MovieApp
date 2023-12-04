package com.test.application.local_data.service

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.test.application.local_data.contacts.ContactsGetter

class ContactsSyncWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val contactsGetter: ContactsGetter
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        return try {
            contactsGetter.fetchAndStoreContacts()
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}