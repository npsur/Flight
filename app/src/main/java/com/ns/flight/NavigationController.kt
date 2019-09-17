package com.ns.flight

interface NavigationController {
    fun navigate(flight: Flight)
    fun setUpButton(enabled: Boolean)
}