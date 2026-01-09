package com.buildkt.product.presentation.list

import androidx.paging.PagingData
import com.buildkt.product.domain.Product
import com.buildkt.mvi.TriggersSideEffect
import kotlinx.coroutines.flow.Flow

sealed interface ProductListIntent {

    @TriggersSideEffect
    data object PaneLaunched : ProductListIntent

    data class ProductsLoaded(val products: Flow<PagingData<Product>>) : ProductListIntent

    @TriggersSideEffect
    data class ProductClicked(val productId: Long) : ProductListIntent
}
