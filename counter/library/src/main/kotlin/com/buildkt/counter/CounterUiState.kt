package com.buildkt.counter

data class CounterUiState(
    val counter: Int = 0,
    val isScreenEnabled: Boolean = true,
) {
    val counterAsText: String get() = counter.toString()
}