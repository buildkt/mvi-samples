package com.buildkt.product.data.remote

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface ProductApiService {

    fun getProductsFlow(): StateFlow<List<ProductDto>>

    suspend fun getProducts(page: Int, limit: Int): List<ProductDto>
}

class FakeProductApiService : ProductApiService {

    private val products = MutableStateFlow(
        (1..50).map {
            ProductDto(
                id = it.toLong(),
                name = "Product $it",
                description = "This is a detailed description for product number $it.",
                price = (10.0 + it * 1.5),
                imageUrl = "https://picsum.photos/id/$it/200/200",
            )
        },
    )

    override fun getProductsFlow(): StateFlow<List<ProductDto>> = products

    override suspend fun getProducts(page: Int, limit: Int): List<ProductDto> {
        val startIndex = (page - 1) * limit
        val endIndex = (startIndex + limit).coerceAtMost(products.value.size)
        delay(timeMillis = 800) // Simulate network latency

        return if (startIndex < products.value.size) {
            products.value.subList(startIndex, endIndex)
        } else {
            emptyList()
        }
    }
}
