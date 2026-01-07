package com.buildkt.counter

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.buildkt.mvi.android.LogMiddleware
import kotlin.collections.plusAssign

fun NavGraphBuilder.counterNavigation(
    navController: NavController,
    counterRepository: CounterRepository = CounterRepository()
) {
    counterPane(navController = navController, route = COUNTER_ROUTE) {
        middlewares += LogMiddleware()

        reducer = counterReducer

        sideEffects {
            decrementCounter = decrementCounter(counterRepository = counterRepository)
            incrementCounter = incrementCounter(counterRepository = counterRepository)
        }
    }
}

const val COUNTER_ROUTE = "counter"
