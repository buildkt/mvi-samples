package com.buildkt.counter

class CounterRepository {

    private var counter = 0

    fun update(newValue: Int) {
        counter = newValue
    }
}