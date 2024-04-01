package com.hashankur.countryflags.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun GoToNextLevel(nextRound: Boolean, action: () -> Unit) {
    ExtendedFloatingActionButton(
        text = { if (!nextRound) Text("Submit") else Text("Next") },
        icon = {
            if (!nextRound) Icon(Icons.Filled.Check, "Submit")
            else Icon(
                Icons.AutoMirrored.Filled.ArrowForward,
                "Next"
            )
        },
        onClick = { action() },
        containerColor = if (nextRound) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
    )
}