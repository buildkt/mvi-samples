package com.buildkt.product

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.buildkt.mvi.android.LogMiddleware
import com.buildkt.mvi.android.NavigationEvent
import com.buildkt.mvi.android.navigate
import com.buildkt.mvi.android.routeTo
import com.buildkt.product.data.ProductRepositoryImpl
import com.buildkt.product.domain.ProductRepository
import com.buildkt.product.presentation.detail.loadProduct
import com.buildkt.product.presentation.detail.productDetailPane
import com.buildkt.product.presentation.detail.productDetailReducer
import com.buildkt.product.presentation.list.ProductListIntent
import com.buildkt.product.presentation.list.loadProductList
import com.buildkt.product.presentation.list.productListPane
import com.buildkt.product.presentation.list.productListReducer
import kotlin.Long
import kotlin.String
import kotlin.collections.plus

fun NavGraphBuilder.productsNavigation(
    navController: NavController,
    route: String,
    productRepository: ProductRepository = ProductRepositoryImpl(),
) = navigation(
    route = route,
    startDestination = ProductList.route,
) {
    productListPane(navController, route = ProductList.route) {
        middlewares + LogMiddleware()

        reducer = productListReducer

        sideEffects {
            paneLaunched = loadProductList(repository = productRepository)

            productClicked = routeTo { _, intent ->
                intent as ProductListIntent.ProductClicked
                DetailProduct(productId = intent.productId)
            }
        }
    }

    productDetailPane(navController, route = DetailProduct.route) {
        middlewares + LogMiddleware()

        reducer = productDetailReducer

        sideEffects {
            backClicked = navigate(event = NavigationEvent.PopBack)

            paneLaunched = loadProduct(repository = productRepository)
        }
    }
}

sealed interface ProductRoute {

    val route: String

    companion object : ProductRoute {
        override val route = "product"
    }
}

data object DetailProduct : ProductRoute {
    override val route = "product?productId={productId}"

    operator fun invoke(productId: Long) = route.replace(oldValue = "{productId}", newValue = productId.toString())
}

data object ProductList : ProductRoute {

    override val route = "product/listing"
}
