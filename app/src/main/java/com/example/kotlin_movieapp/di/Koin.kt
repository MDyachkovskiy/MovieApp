package com.example.kotlin_movieapp.di

import com.test.application.local_data.contacts.ContactsGetter
import com.test.application.local_data.service.ContactsSyncWorkerFactory
import org.koin.dsl.module

val workerModule = module {
    single { ContactsGetter(get(), get()) }
    factory { ContactsSyncWorkerFactory(get()) }
}