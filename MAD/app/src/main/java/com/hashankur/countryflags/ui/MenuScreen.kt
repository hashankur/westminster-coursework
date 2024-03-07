package com.hashankur.countryflags.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MenuButton(name: String, onClick: () -> Unit) {
    Button(
        onClick,
        Modifier.width(250.dp)
    )
    {
        Text(name)
    }
}

@Composable
fun MenuScreen(
    onNavigateToGuessCountry: () -> Unit,
    onNavigateToGuessHints: () -> Unit,
    onNavigateToGuessFlag: () -> Unit,
    onNavigateToAdvancedLevel: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MenuButton("Guess the Country", onClick = onNavigateToGuessCountry)
        MenuButton("Guess Hints", onClick = onNavigateToGuessHints)
        MenuButton("Guess the Flag", onClick = onNavigateToGuessFlag)
        MenuButton("Advanced Level", onClick = onNavigateToAdvancedLevel)
    }
}
