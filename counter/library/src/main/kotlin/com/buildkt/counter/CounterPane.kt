package com.buildkt.counter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.buildkt.material3.ExtendedMaterialTheme
import com.buildkt.material3.components.Button
import com.buildkt.material3.components.ButtonStyle
import com.buildkt.material3.components.ScreenScaffold
import com.buildkt.material3.tokens.spacers
import com.buildkt.mvi.MviScreen

@Composable
@MviScreen(
    uiState = CounterUiState::class,
    intent = CounterIntent::class,
)
fun CounterPane(
    state: CounterUiState,
    onIntent: (CounterIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    ScreenScaffold(
        title = "Counter",
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacers.medium)
        ) {
            Text(
                text = state.counterAsText,
                style = MaterialTheme.typography.bodyLarge,
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacers.medium)
            ) {
                Button(
                    onClick = { onIntent(CounterIntent.DecrementCounter) },
                    style = ButtonStyle.Outlined,
                    enabled = state.isScreenEnabled,
                    text = "Decrement",
                )
                Button(
                    onClick = { onIntent(CounterIntent.IncrementCounter) },
                    enabled = state.isScreenEnabled,
                    text = "Increment",
                )
            }
        }
    }
}

@Composable
@Preview
private fun PreviewCounterPane() = ExtendedMaterialTheme {
    CounterPane(
        state = CounterUiState(counter = 1),
        onIntent = { },
    )
}

@Composable
@Preview
private fun PreviewCounterLoadingPane() = ExtendedMaterialTheme {
    CounterPane(
        state = CounterUiState(isScreenEnabled = true),
        onIntent = { },
    )
}

