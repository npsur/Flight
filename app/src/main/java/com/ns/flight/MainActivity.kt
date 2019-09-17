package com.ns.flight

import android.os.Bundle
import com.ns.flight.ui.detail.FlightDetailFragment
import com.ns.flight.ui.flightlist.FlightListFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), NavigationController {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setTitle(R.string.toolbar_title)
        supportActionBar?.setHomeButtonEnabled(true)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    FlightListFragment.newInstance(),
                    FlightListFragment::class.java.canonicalName
                )
                .commit()
        }
    }

    override fun navigate(flight: Flight) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                FlightDetailFragment.newInstance(flight),
                FlightDetailFragment::class.java.canonicalName
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    override fun setUpButton(enabled: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(enabled)
    }
}
