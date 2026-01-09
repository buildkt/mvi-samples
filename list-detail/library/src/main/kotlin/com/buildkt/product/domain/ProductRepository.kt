package com.buildkt.product.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProducts(): Flow<PagingData<Product>>

    suspend fun getProduct(productId: Long): Product
}