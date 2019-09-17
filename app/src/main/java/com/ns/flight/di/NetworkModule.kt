package com.ns.flight.di

import com.ns.flight.ApiService
import com.ns.flight.FlightJsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    @Singleton
    @Provides
    @JvmStatic
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(FlightJsonAdapter())
            .build()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://firebasestorage.googleapis.com/v0/b/flight-centre-brand.appspot.com/o/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}