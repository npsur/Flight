package com.ns.flight

import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

object FlightFactory {
    fun flight20Jan2019(id: Int = 1): Flight {
        val departure = ZonedDateTime.of(2019,
            1,
            20,
            9,
            30,
            0,
            0,
            ZoneId.of("UTC"))

        val arrival = departure.plusHours(2).plusMinutes(2)

        return Flight(id,
                departure,
                "AAB",
                "FB207",
                "Melbourne",
                "MEL",
                "Brisbane",
                "BRB",
                "2hr2min",
                arrival)
    }

    fun flight22Jan2019(id: Int = 1): Flight {
        val departure = ZonedDateTime.of(2019,
            1,
            22,
            9,
            30,
            0,
            0,
            ZoneId.of("UTC"))

        val arrival = departure.plusHours(2).plusMinutes(2)

        return Flight(id,
            departure,
            "AAB",
            "FB207",
            "Melbourne",
            "MEL",
            "Brisbane",
            "BRB",
            "2hr2min",
            arrival)
    }

    fun flight25Jan2019(id: Int = 1): Flight {
        val departure = ZonedDateTime.of(2019,
            1,
            25,
            9,
            30,
            0,
            0,
            ZoneId.of("UTC"))

        val arrival = departure.plusHours(2).plusMinutes(2)

        return Flight(id,
            departure,
            "AAB",
            "FB207",
            "Melbourne",
            "MEL",
            "Brisbane",
            "BRB",
            "2hr2min",
            arrival)
    }
}