package com.ns.flight

import com.ns.flight.ui.flightlist.FlightListRepository
import com.ns.flight.ui.flightlist.LoadFlightDataUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FlightListViewModelTest {
    lateinit var apiService: ApiService
    lateinit var repository: FlightListRepository

    @Before
    fun setUp() {
        apiService = object: ApiService {
            override suspend fun flights(alt: String, token: String): List<Flight> {
                return listOf(
                    FlightFactory.flight20Jan2019(1),
                    FlightFactory.flight20Jan2019(2),
                    FlightFactory.flight22Jan2019(3),
                    FlightFactory.flight20Jan2019(4),
                    FlightFactory.flight25Jan2019(5)
                )
            }
        }

        repository = FlightListRepository(apiService)
    }

    @Test
    fun testLoadFlightData() = runBlocking {
        val sorted = listOf(
            FlightFactory.flight25Jan2019(5),
            FlightFactory.flight22Jan2019(3),
            FlightFactory.flight20Jan2019(1),
            FlightFactory.flight20Jan2019(2),
            FlightFactory.flight20Jan2019(4)
        )

        val indexedMap = linkedMapOf(
            FlightFactory.flight25Jan2019().departureDate.toLocalDate() to 0,
            FlightFactory.flight22Jan2019().departureDate.toLocalDate() to 1,
            FlightFactory.flight20Jan2019().departureDate.toLocalDate() to 2
        )

        val useCase = LoadFlightDataUseCase(repository)
        val result = useCase.execute()
        assertTrue(result is Result.Success)

        val data = (result as Result.Success).data
        assertEquals(sorted, data.flights)
        assertTrue(indexedMap == data.map)
    }
}
