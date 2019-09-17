package com.ns.flight.ui.flightlist

import com.ns.flight.ApiService
import com.ns.flight.BuildConfig
import com.ns.flight.Flight
import javax.inject.Inject

class FlightListRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun get(): List<Flight> {
        return apiService.flights("media", BuildConfig.TOKEN)
    }
}