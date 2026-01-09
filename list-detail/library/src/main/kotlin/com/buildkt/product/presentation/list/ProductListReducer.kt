package com.buildkt.product.presentation.list

import com.buildkt.mvi.Reducer

val productListReducer = Reducer<ProductListUiState, ProductListIntent> { state, intent ->
    when (intent) {
        is ProductListIntent.PaneLaunched -> state
        is ProductListIntent.ProductsLoaded -> state.copy(products = intent.products)
        is ProductListIntent.ProductClicked -> state
    }
}
