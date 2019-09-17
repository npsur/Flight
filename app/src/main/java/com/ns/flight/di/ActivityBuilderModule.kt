package com.ns.flight.di

import com.ns.flight.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            FlightListModule::class
        ]
    )
    abstract fun mainActivity(): MainActivity
}