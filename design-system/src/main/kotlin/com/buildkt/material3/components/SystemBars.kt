package com.buildkt.material3.components

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * A data class representing the desired style for the system status bar.
 * It allows for customization of the status bar's color and the appearance of its icons.
 *
 * This is used in conjunction with [SystemBarsEffect] to apply the style to the current window.
 *
 * @property statusBarColor The background color to apply to the system status bar.
 * @property darkIcons A boolean indicating whether the status bar icons (like time, battery, and
 * notifications) should be drawn in a dark color. This should be `true` for light status bar
 * backgrounds and `false` for dark backgrounds.
 */
data class SystemBarsStyle(
    val statusBarColor: Color,
    val darkIcons: Boolean,
)

/**
 * A collection of predefined default styles for the system bars.
 */
object SystemBarsDefaults {
    /**
     * The default system bars style for the application.
     * It uses a transparent status bar and requests dark icons, suitable for screens with a
     * light-colored top app bar that sits behind the status bar.
     */
    val style = SystemBarsStyle(
        statusBarColor = Color.Transparent,
        darkIcons = true
    )
}

/**
 * A Composable side effect that applies a given [SystemBarsStyle] to the app's window.
 *
 * This function should be called from within a top-level Composable in your theme or screen.
 * It uses a [SideEffect] to imperatively change the system status bar color and icon appearance
 * whenever the `style` parameter changes.
 *
 * @param style The [SystemBarsStyle] to apply.
 */
@Composable
fun SystemBarsEffect(style: SystemBarsStyle) {
    val view = LocalView.current

    SideEffect {
        val window = (view.context as? Activity)?.window ?: return@SideEffect

        window.statusBarColor = style.statusBarColor.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = style.darkIcons
    }
}
