package com.buildkt.product.presentation.list

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.buildkt.product.domain.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
data class ProductListUiState(
    val products: Flow<PagingData<Product>> = emptyFlow(),
)
