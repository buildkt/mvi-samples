package com.buildkt.product.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.buildkt.product.domain.Product
import com.buildkt.material3.ExtendedMaterialTheme
import com.buildkt.material3.components.ScreenScaffold
import com.buildkt.material3.tokens.spacers
import com.buildkt.mvi.MviScreen
import com.buildkt.mvi.NavArgument
import java.math.BigDecimal

@MviScreen(
    uiState = ProductDetailUiState::class,
    intent = ProductDetailIntent::class,
)
@Composable
fun ProductDetailPane(
    @NavArgument productId: Long,
    state: ProductDetailUiState,
    onIntent: (ProductDetailIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(productId) {
        onIntent(ProductDetailIntent.PaneLaunched(productId))
    }

    ScreenScaffold(
        modifier = modifier,
        title = state.product?.name ?: "",
        isLoading = state.isLoading,
        onBackClicked = { onIntent(ProductDetailIntent.BackClicked) },
    ) {
        state.product?.let { product ->
            ProductContent(product = product)
        }
    }
}

@Composable
private fun ProductContent(
    product: Product,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
    ) {
        item {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = "Image for ${product.name}",
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(4f / 3f),
            )
        }

        item {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacers.medium),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacers.small),
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacers.medium))
                Text(
                    text = "About this product",
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailPanePreview() {
    val sampleProduct =
        Product(
            id = 1L,
            name = "Modern Wireless Headphones",
            description =
                """
                Experience crystal clear audio with these stylish over-ear headphones. Features active noise cancellation, a 
                20-hour battery life, and seamless bluetooth connectivity. Perfect for music lovers and professionals alike.
                """.trimIndent(),
            price = BigDecimal("199.99"),
            imageUrl = "https://picsum.photos/id/1082/800/600",
        )
    ExtendedMaterialTheme {
        ProductDetailPane(
            productId = 1L,
            state =
                ProductDetailUiState(
                    product = sampleProduct,
                    isLoading = false,
                ),
            onIntent = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailPaneLoadingPreview() {
    ExtendedMaterialTheme {
        ProductDetailPane(
            productId = 1L,
            state = ProductDetailUiState(isLoading = true),
            onIntent = { },
        )
    }
}
