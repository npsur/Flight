package com.ns.flight

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.ZonedDateTime

@Parcelize
data class Flight(
    val id: Int,
    val departureDate: ZonedDateTime,
    val airlineCode: String,
    val flightNum: String,
    val departureCity: String,
    val departureAirport: String,
    val arrivalCity: String,
    val arrivalAirport: String,
    val duration: String,
    val arrivalDate: ZonedDateTime
) : Parcelable