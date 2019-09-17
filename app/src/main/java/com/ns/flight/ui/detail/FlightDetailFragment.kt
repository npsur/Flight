package com.ns.flight.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ns.flight.Flight
import com.ns.flight.NavigationController
import com.ns.flight.databinding.FragmentFlightDetailBinding
import javax.inject.Inject

class FlightDetailFragment : Fragment() {
    companion object {
        const val FLIGHT = "flight"

        fun newInstance(flight: Flight): FlightDetailFragment {
            val fragment = FlightDetailFragment()
            fragment.arguments = Bundle().apply {
                putParcelable(FLIGHT, flight)
            }
            return fragment
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentFlightDetailBinding
    private var navController: NavigationController? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationController) {
            navController = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlightDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navController?.setUpButton(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireArguments().run {
            val flight = getParcelable<Flight>(FLIGHT)
            binding.flight = flight
        }
    }

    override fun onDetach() {
        navController = null
        super.onDetach()
    }
}