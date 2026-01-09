package com.buildkt.product.data.remote

import com.buildkt.product.domain.Product
import java.math.BigDecimal

data class ProductDto(
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
)

fun ProductDto.toDomain(): Product = Product(
    id = this.id,
    name = this.name,
    description = this.description,
    price = BigDecimal.valueOf(this.price),
    imageUrl = this.imageUrl,
)
