package com.ns.flight.ui.flightlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ns.flight.Flight
import com.ns.flight.databinding.ItemFlightBinding

class FlightAdapter(
    private val eventListener: EventListener
) : ListAdapter<Flight, FlightViewHolder>(FlightDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val binding = ItemFlightBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return FlightViewHolder(binding, eventListener)
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class FlightViewHolder(
    private val binding: ItemFlightBinding,
    private val eventListener: EventListener
) : RecyclerView.ViewHolder(binding.root) {
        fun bind(flight: Flight) {
            binding.eventListener = eventListener
            binding.flight = flight
            binding.executePendingBindings()
        }
}

class FlightDiffCallback : DiffUtil.ItemCallback<Flight>() {
    override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem == newItem
    }
}