package com.hashankur.countryflags.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GuessHintsScreen() {
    val countries = readJSON()
    val countryKeys = countries.keys().asSequence().toList()
    val countryValues = countryKeys.map { countries[it] as String }.toList().sorted()

    // TODO: do not repeat the same country
    val random = rememberSaveable { mutableStateOf(countryKeys.random()) }

    val input = rememberSaveable { mutableStateOf("") }
    val dashes =
        rememberSaveable { mutableStateOf("_ ".repeat(countries[random.value].toString().length)) }
    val guesses = rememberSaveable { mutableSetOf<Char>() }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .fillMaxHeight()
    ) {
        Text(countries[random.value].toString())
        DisplayFlagByCountryCode(countryCode = random.value)
        Text(dashes.value, fontFamily = FontFamily.Monospace, fontSize = 24.sp)
        OutlinedTextField(
            value = input.value,
            onValueChange = { newValue ->
                input.value = newValue.take(1) // Limit to max 1 character
            },
            label = { Text("Type a character") },
            modifier = Modifier.fillMaxWidth(),
        )
        Button(onClick = {
            containsChar(countries[random.value] as String, input.value[0], dashes, guesses)
            input.value = "" // Reset input
        }) {
            Text("Submit")
        }
    }
}

fun containsChar(
    word: String,
    char: Char,
    dashes: MutableState<String>,
    guesses: MutableSet<Char>
) {
    if (word.contains(char, ignoreCase = true)) {
        guesses.add(char)
    }
    dashes.value =
        word.map { if (guesses.contains(it.lowercaseChar())) it else '_' }.joinToString(" ") + " "
}
