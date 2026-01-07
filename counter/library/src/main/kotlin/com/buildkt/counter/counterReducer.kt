package com.buildkt.counter

import com.buildkt.mvi.Reducer

val counterReducer = Reducer<CounterUiState, CounterIntent> { state, intent ->
    when (intent) {
        is CounterIntent.CounterUpdated -> state.copy(
            isScreenEnabled = true,
            counter = intent.newValue
        )

        CounterIntent.DecrementCounter -> state.copy(
            isScreenEnabled = false
        )

        CounterIntent.IncrementCounter -> state.copy(
            isScreenEnabled = false
        )
    }
}
