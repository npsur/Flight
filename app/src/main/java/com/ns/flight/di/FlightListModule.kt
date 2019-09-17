package com.ns.flight.di

import androidx.lifecycle.ViewModel
import com.ns.flight.ui.flightlist.FlightListFragment
import com.ns.flight.ui.flightlist.FlightListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FlightListModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeFlightListFragment(): FlightListFragment

    @Binds
    @IntoMap
    @ViewModelKey(FlightListViewModel::class)
    abstract fun bindFlightListViewModel(viewModel: FlightListViewModel): ViewModel
}