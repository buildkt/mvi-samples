package com.buildkt.product.presentation.detail

import com.buildkt.product.domain.Product
import com.buildkt.mvi.TriggersSideEffect

sealed interface ProductDetailIntent {

    @TriggersSideEffect
    data class PaneLaunched(val productId: Long) : ProductDetailIntent

    data class LoadSuccess(val product: Product) : ProductDetailIntent

    data class LoadFailure(val message: String) : ProductDetailIntent

    @TriggersSideEffect
    data object BackClicked : ProductDetailIntent
}
