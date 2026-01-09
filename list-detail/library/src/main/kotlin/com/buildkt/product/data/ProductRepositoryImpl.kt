package com.buildkt.product.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.buildkt.product.data.remote.FakeProductApiService
import com.buildkt.product.data.remote.ProductApiService
import com.buildkt.product.data.remote.ProductPagingSource
import com.buildkt.product.data.remote.toDomain
import com.buildkt.product.domain.Product
import com.buildkt.product.domain.ProductRepository
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    private val apiService: ProductApiService = FakeProductApiService()
) : ProductRepository {

    override fun getProducts(): Flow<PagingData<Product>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { ProductPagingSource(apiService) },
    ).flow

    override suspend fun getProduct(productId: Long): Product = apiService
        .getProductsFlow()
        .value
        .find { it.id == productId }
        ?.toDomain()
        ?: throw NoSuchElementException("Product with ID $productId not found.")
}
