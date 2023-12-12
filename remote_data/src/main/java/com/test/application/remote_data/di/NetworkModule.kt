package com.test.application.remote_data.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.test.application.core.utils.KINOPOISK_DOMAIN
import com.test.application.remote_data.BuildConfig
import com.test.application.remote_data.api.KinopoiskService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val requestWithHeader = originalRequest.newBuilder()
                    .header("x-api-key", BuildConfig.KINOPOISK_API_KEY)
                    .build()
                chain.proceed(requestWithHeader)
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(KINOPOISK_DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideKinopoiskService(retrofit: Retrofit): KinopoiskService {
        return retrofit.create(KinopoiskService::class.java)
    }
}