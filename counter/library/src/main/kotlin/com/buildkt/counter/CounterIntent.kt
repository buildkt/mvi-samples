package com.buildkt.counter

import com.buildkt.mvi.TriggersSideEffect

sealed interface CounterIntent {
    @TriggersSideEffect
    data object IncrementCounter : CounterIntent
    @TriggersSideEffect
    data object DecrementCounter : CounterIntent
    @TriggersSideEffect
    data class CounterUpdated(val newValue: Int) : CounterIntent
}
