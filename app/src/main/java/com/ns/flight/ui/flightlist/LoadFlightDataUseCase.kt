package com.ns.flight.ui.flightlist

import android.util.Log
import com.ns.flight.Result
import javax.inject.Inject

class LoadFlightDataUseCase @Inject constructor(
    private val repository: FlightListRepository
) : UseCase<Nothing, FlightData> {
    /**
     * Map sorted distinct local dates to the first flight with the same departure date.
     * This is going to be used later by [DateSeparatorItemDecoration] to determine at which
     * position the date header should be shown.
     */
    override suspend fun execute(): Result<FlightData> {
        return try {
            val flights = repository.get()
                .sortedByDescending { it.departureDate }

            val map = flights.map { it.departureDate.toLocalDate() }
                .distinct()
                .associateWith { date ->
                    flights.indexOfFirst {
                        date == it.departureDate.toLocalDate()
                    }
                }

            Result.Success(FlightData(flights, map))
        } catch (e: Exception) {
            Log.e("Error", null, e)
            Result.Error(e)
        }
    }
}

interface UseCase<Input, Output> {
    suspend fun execute(): Result<Output>
}