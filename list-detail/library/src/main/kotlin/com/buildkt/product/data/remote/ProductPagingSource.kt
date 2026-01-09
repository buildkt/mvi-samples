package com.buildkt.product.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.buildkt.product.domain.Product

class ProductPagingSource(
    private val apiService: ProductApiService,
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> =
        try {
            val page = params.key ?: 1
            val productsDto = apiService.getProducts(page = page, limit = params.loadSize)
            LoadResult.Page(
                data = productsDto.map { it.toDomain() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (productsDto.isEmpty()) null else page + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
}
