package com.buildkt.product.domain

import androidx.compose.runtime.Immutable
import java.math.BigDecimal

@Immutable
data class Product(
    val id: Long,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val imageUrl: String,
)
