package com.buildkt.product.presentation.detail

import com.buildkt.product.domain.ProductRepository
import com.buildkt.mvi.sideEffect

fun loadProduct(repository: ProductRepository) =
    sideEffect<ProductDetailUiState, ProductDetailIntent> { _, intent ->
        intent as ProductDetailIntent.PaneLaunched
        val product = repository.getProduct(productId = intent.productId)

        ProductDetailIntent.LoadSuccess(product)
    }
