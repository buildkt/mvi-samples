package com.buildkt.counter

import com.buildkt.mvi.sideEffect

fun decrementCounter(counterRepository: CounterRepository) = sideEffect<CounterUiState, CounterIntent> { state, _ ->
    val newValue = state.counter - 1
    counterRepository.update(newValue = newValue)

    CounterIntent.CounterUpdated(newValue = newValue)
}

fun incrementCounter(counterRepository: CounterRepository) = sideEffect<CounterUiState, CounterIntent> { state, _ ->
    val newValue = state.counter + 1
    counterRepository.update(newValue = newValue)

    CounterIntent.CounterUpdated(newValue = newValue)
}
