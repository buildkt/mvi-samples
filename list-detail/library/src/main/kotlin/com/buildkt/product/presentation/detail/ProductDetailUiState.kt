package com.buildkt.product.presentation.detail

import androidx.compose.runtime.Immutable
import com.buildkt.product.domain.Product

@Immutable
data class ProductDetailUiState(
    val product: Product? = null,
    val isLoading: Boolean = true,
)
