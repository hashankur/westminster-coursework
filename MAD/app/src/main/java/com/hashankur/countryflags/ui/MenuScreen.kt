package com.hashankur.countryflags.ui

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuButton(name: String, onClick: () -> Unit) {
    Button(
        onClick,
        Modifier
            .width(400.dp)
            .height(100.dp)
            .padding(20.dp)
    )
    {
        Text(name, fontSize = 20.sp)
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
        verticalArrangement = Arrangement.Center,
    ) {
        val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
        val animatedColor by infiniteTransition.animateColor(
            initialValue = MaterialTheme.colorScheme.primary,
            targetValue = MaterialTheme.colorScheme.secondary,
            animationSpec = infiniteRepeatable(tween(2000), RepeatMode.Reverse),
            label = "color"
        )
        Text(
            "Choose\nYour\nMode.",
            modifier = Modifier
                .padding(horizontal = 50.dp)
                .padding(bottom = 50.dp)
                .fillMaxWidth(),
            fontSize = 64.sp,
            lineHeight = 80.sp,
            fontWeight = FontWeight.Bold,
            color = animatedColor // https://developer.android.com/jetpack/compose/animation/quick-guide#animate-text-color
        )
        MenuButton("Guess the Country", onClick = onNavigateToGuessCountry)
        MenuButton("Guess Hints", onClick = onNavigateToGuessHints)
        MenuButton("Guess the Flag", onClick = onNavigateToGuessFlag)
        MenuButton("Advanced Level", onClick = onNavigateToAdvancedLevel)
    }
}
