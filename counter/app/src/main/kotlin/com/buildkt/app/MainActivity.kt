package com.buildkt.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.buildkt.counter.COUNTER_ROUTE
import com.buildkt.counter.counterNavigation
import com.buildkt.material3.ExtendedMaterialTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent { ExtendedMaterialTheme { MainScreen() } }
    }
}

@Composable
private fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = COUNTER_ROUTE,
        modifier = modifier,
    ) {
        counterNavigation(navController = navController)
    }
}
