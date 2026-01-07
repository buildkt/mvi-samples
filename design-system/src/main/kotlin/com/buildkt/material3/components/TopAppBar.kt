package com.buildkt.material3.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * A themed, opinionated wrapper around Material 3's `TopAppBar`, designed to be the
 * standard app bar within the buildkt design system.
 *
 * @param title The text title to be displayed in the app bar.
 * @param modifier The [Modifier] to be applied to this app bar.
 * @param navigationIcon A composable slot for the navigation icon.
 * @param actions A composable slot for actions to be displayed at the end of the app bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    val colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.onPrimary,
        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
        actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
    )

    TopAppBar(
        title = { Text(text = title, style = MaterialTheme.typography.headlineSmall, color = colors.titleContentColor) },
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        colors = colors,
    )
}

/**
 * A convenience overload of [TopAppBar] that simplifies the common use case of
 * displaying a back arrow as the navigation icon in a surface background.
 *
 * @param title The text title to be displayed in the app bar.
 * @param onBackClicked The lambda to be invoked when the back arrow is clicked. If this
 *                      lambda is null, the back arrow will not be displayed.
 * @param backArrowIcon The [ImageVector] to use for the back arrow.
 * @param modifier The [Modifier] to be applied to this app bar.
 * @param actions A composable slot for actions to be displayed at the end of the app bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurfaceTopAppBar(
    title: String,
    onBackClicked: (() -> Unit)?,
    modifier: Modifier = Modifier,
    backArrowIcon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = { Text(text = title, style = MaterialTheme.typography.headlineSmall) },
        modifier = modifier,
        navigationIcon = {
            if (onBackClicked != null) {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        imageVector = backArrowIcon,
                        contentDescription = "Back",
                    )
                }
            }
        },
        actions = actions,
    )
}
