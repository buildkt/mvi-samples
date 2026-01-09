package com.buildkt.product.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import com.buildkt.product.domain.Product
import com.buildkt.material3.ExtendedMaterialTheme
import com.buildkt.material3.components.ScreenScaffold
import com.buildkt.material3.tokens.spacers
import com.buildkt.mvi.MviScreen
import com.buildkt.mvi.android.CollectUiEvents
import com.buildkt.mvi.android.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import java.math.BigDecimal

@MviScreen(
    uiState = ProductListUiState::class,
    intent = ProductListIntent::class
)
@Composable
fun ProductListPane(
    state: ProductListUiState,
    onIntent: (ProductListIntent) -> Unit,
    uiEvents: Flow<UiEvent>,
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val lazyPagingItems = state.products.collectAsLazyPagingItems()

    LaunchedEffect(Unit) { onIntent(ProductListIntent.PaneLaunched) }
    CollectUiEvents(uiEvents, snackbarHostState)

    ScreenScaffold(
        title = "Products",
        snackbarHostState = snackbarHostState,
        modifier = modifier,
    ) {
        ProductGrid(
            lazyPagingItems = lazyPagingItems,
            onProductClicked = { productId -> onIntent(ProductListIntent.ProductClicked(productId)) },
        )
    }
}

@Composable
private fun ProductGrid(
    modifier: Modifier = Modifier,
    lazyPagingItems: LazyPagingItems<Product>,
    onProductClicked: (Long) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Adaptive(minSize = 160.dp),
        contentPadding = PaddingValues(all = MaterialTheme.spacers.medium),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacers.medium),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacers.medium),
    ) {
        items(
            count = lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey { it.id },
        ) { index ->
            val product = lazyPagingItems[index]
            if (product != null) {
                ProductCard(
                    product = product,
                    onClick = { onProductClicked(product.id) },
                )
            }
        }

        if (lazyPagingItems.loadState.append is LoadState.Loading) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = MaterialTheme.spacers.large),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun ProductCard(
    product: Product,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.large,
    ) {
        Column {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomStart,
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = "Image for ${product.name}",
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.large),
                )
                // Scrim for better text readability
                Box(
                    modifier =
                        Modifier
                            .matchParentSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                                    startY = 200f,
                                ),
                            ),
                )
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White,
                    modifier = Modifier.padding(MaterialTheme.spacers.medium),
                )
            }

            Column(
                modifier =
                    Modifier.padding(
                        start = MaterialTheme.spacers.medium,
                        end = MaterialTheme.spacers.medium,
                        top = MaterialTheme.spacers.small,
                        bottom = MaterialTheme.spacers.medium,
                    ),
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(Modifier.height(MaterialTheme.spacers.extraSmall))
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductListPanePreview() {
    val sampleProducts =
        List(10) { i ->
            Product(
                id = i.toLong(),
                name = "Product Name $i",
                description = "This is a short but engaging description for the product.",
                price = BigDecimal.valueOf(19.99 + i * 5),
                imageUrl = "https://picsum.photos/id/${20 + i}/200",
            )
        }

    ExtendedMaterialTheme {
        ProductListPane(
            state =
                ProductListUiState(
                    products = flowOf(PagingData.from(sampleProducts)),
                ),
            onIntent = {},
            uiEvents = emptyFlow(),
        )
    }
}
