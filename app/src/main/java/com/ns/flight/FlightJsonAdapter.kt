package com.ns.flight

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

@Suppress("unused")
class FlightJsonAdapter {
    @FromJson
    fun flightFromJson(json: FlightJson): Flight {
        val departureDate = parseIsoDateTime(json.departure_date)
        val arrivalDate = parseIsoDateTime(json.arrival_date)
        return Flight(id = json.id,
            departureDate = departureDate,
            airlineCode = json.airline_code,
            flightNum = json.flight_number,
            departureCity = json.departure_city.split(",")[0],
            departureAirport = json.departure_airport,
            arrivalCity = json.arrival_city.split(",")[0],
            arrivalAirport = json.arrival_airport,
            duration = json.scheduled_duration,
            arrivalDate = arrivalDate)
    }

    @ToJson
    fun flightToJson(flight: Flight): FlightJson {
        val departureDate = flight.departureDate.toLocalDateTime().toString()
        val arrivalDate = flight.departureDate.toLocalDateTime().toString()
        return FlightJson(id = flight.id,
            departure_date = departureDate,
            airline_code = flight.airlineCode,
            flight_number = flight.flightNum,
            departure_city = flight.departureCity,
            departure_airport = flight.departureAirport,
            arrival_city = flight.arrivalCity,
            arrival_airport = flight.arrivalAirport,
            scheduled_duration = flight.duration,
            arrival_date = arrivalDate)
    }
}

class FlightJson(
    val id: Int,
    val departure_date: String,
    val airline_code: String,
    val flight_number: String,
    val departure_city: String,
    val departure_airport: String,
    val arrival_city: String,
    val arrival_airport: String,
    val scheduled_duration: String,
    val arrival_date: String
)

fun parseIsoDateTime(date: String) =
    ZonedDateTime.parse(date,
        DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.of("UTC")))