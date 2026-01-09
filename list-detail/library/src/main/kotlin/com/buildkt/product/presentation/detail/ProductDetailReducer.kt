package com.buildkt.product.presentation.detail

import com.buildkt.mvi.Reducer

val productDetailReducer = Reducer<ProductDetailUiState, ProductDetailIntent> { state, intent ->
    when (intent) {
        is ProductDetailIntent.PaneLaunched -> state.copy(isLoading = true)
        is ProductDetailIntent.LoadSuccess -> state.copy(isLoading = false, product = intent.product)
        is ProductDetailIntent.LoadFailure -> state.copy(isLoading = false)
        is ProductDetailIntent.BackClicked -> state
    }
}
