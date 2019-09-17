package com.ns.flight.ui.flightlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ns.flight.NavigationController
import com.ns.flight.databinding.FragmentFlightListBinding
import com.ns.flight.util.EventObserver
import com.ns.flight.util.clearDecorations
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FlightListFragment : DaggerFragment() {
    companion object {
        fun newInstance() = FlightListFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var flightListViewModel: FlightListViewModel
    private lateinit var binding: FragmentFlightListBinding
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
    ): View {
        flightListViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(FlightListViewModel::class.java)

        binding = FragmentFlightListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = flightListViewModel
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navController?.setUpButton(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val flightsAdapter = FlightAdapter(flightListViewModel)
        binding.rvFlight.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = flightsAdapter
        }

        flightListViewModel.flights.observe(this, Observer {
            flightsAdapter.submitList(it.flights)
            binding.rvFlight.run {
                clearDecorations()
                addItemDecoration(SpaceItemDecoration(context))
                addItemDecoration(DateSeparatorItemDecoration(context, it.map))
            }
        })

        flightListViewModel.toFlightDetail.observe(this, EventObserver {
            navController?.navigate(it)
        })

        flightListViewModel.error.observe(this, EventObserver {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onDetach() {
        navController = null
        super.onDetach()
    }
}
