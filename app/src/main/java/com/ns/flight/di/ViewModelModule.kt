package com.ns.flight.di

import androidx.lifecycle.ViewModelProvider
import com.ns.flight.FlightViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: FlightViewModelFactory): ViewModelProvider.Factory
}