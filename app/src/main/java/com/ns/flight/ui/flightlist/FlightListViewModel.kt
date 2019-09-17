package com.ns.flight.ui.flightlist

import androidx.lifecycle.*
import com.ns.flight.ErrorMessage
import com.ns.flight.Flight
import com.ns.flight.Result
import com.ns.flight.util.Event
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

class FlightListViewModel @Inject constructor(
    loadFlightDataUseCase: LoadFlightDataUseCase
) : ViewModel(), EventListener {
    private val _error = MediatorLiveData<Event<ErrorMessage>>()
    val error: LiveData<Event<ErrorMessage>>
        get() = _error

    private val _loading = MediatorLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _flights = MediatorLiveData<FlightData>()
    val flights: LiveData<FlightData>
        get() = _flights

    private val _toFlightDetail = MutableLiveData<Event<Flight>>()
    val toFlightDetail: LiveData<Event<Flight>>
        get() = _toFlightDetail

    private val loadFlightsResult = MutableLiveData<Result<FlightData>>()

    init {
        _flights.addSource(loadFlightsResult) { result ->
            if (result is Result.Success) {
                _flights.value = result.data
            }
        }

        _error.addSource(loadFlightsResult) { result ->
            if (result is Result.Error) {
                _error.value = Event(ErrorMessage(result.exception.message ?: "Error"))
            }
        }

        viewModelScope.launch {
            _loading.postValue(true)
            loadFlightsResult.postValue(loadFlightDataUseCase.execute())
            _loading.postValue(false)
        }
    }

    override fun onFlightClicked(flight: Flight) {
        _toFlightDetail.postValue(Event(flight))
    }
}

data class FlightData(val flights: List<Flight>, val map: Map<LocalDate, Int>)

interface EventListener {
    fun onFlightClicked(flight: Flight)
}