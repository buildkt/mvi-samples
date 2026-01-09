package com.buildkt.product.presentation.list

import com.buildkt.product.domain.ProductRepository
import com.buildkt.mvi.sideEffect

fun loadProductList(repository: ProductRepository) = sideEffect<ProductListUiState, ProductListIntent> { _, _ ->
    val products = repository.getProducts()

    ProductListIntent.ProductsLoaded(products = products)
}
