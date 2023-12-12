package com.example.kotlin_movieapp.di

import android.content.Context
import com.test.application.local_data.contacts.ContactsDao
import com.test.application.local_data.contacts.ContactsGetter
import com.test.application.local_data.service.ContactsSyncWorkerFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class WorkerModule {

    @Provides
    fun provideContactsGetter(
        @ApplicationContext context: Context, contactsDao: ContactsDao
    ): ContactsGetter {
        return ContactsGetter(context, contactsDao)
    }

    @Provides
    fun provideContactsSyncWorkFactory(contactsGetter: ContactsGetter): ContactsSyncWorkerFactory {
        return ContactsSyncWorkerFactory(contactsGetter)
    }
}