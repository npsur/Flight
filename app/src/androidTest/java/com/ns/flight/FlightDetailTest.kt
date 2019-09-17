package com.ns.flight

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.ns.flight.ui.detail.FlightDetailFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

@RunWith(AndroidJUnit4::class)
class FlightDetailTest {
    @get:Rule
    val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
        val flight = createFlight(1)
        activityRule.activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container, FlightDetailFragment.newInstance(flight))
            .commit()
    }

    @Test
    fun testDetailFragment() {
        onView(withId(R.id.tv_departure_airport))
            .check(matches(withText("MEL")))

        onView(withId(R.id.tv_departure_time))
            .check(matches(withText("Sun, 20 Jan 09:30 AM")))

        onView(withId(R.id.tv_destination_airport))
            .check(matches(withText("BRB")))

        onView(withId(R.id.tv_destination_time))
            .check(matches(withText("Sun, 20 Jan 11:32 AM")))

        onView(withId(R.id.tv_flight_name))
            .check(matches(withText("Flight : AAB207")))
    }

    private fun createFlight(id: Int): Flight {
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
            "207",
            "Melbourne",
            "MEL",
            "Brisbane",
            "BRB",
            "2hr2min",
            arrival)
    }
}