package com.hashankur.countryflags.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hashankur.countryflags.FlagImage
import com.hashankur.countryflags.R
import com.hashankur.countryflags.countryKeyValues
import com.hashankur.countryflags.flagByCountryCode
import com.hashankur.countryflags.ui.components.TopBarBuilder
import com.hashankur.countryflags.ui.theme.CountryFlagsTheme

class GuessHintsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountryFlagsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            TopBarBuilder(
                                getString(R.string.mode2), goBack = { finish() }
                            )
                        }
                    ) { innerPadding ->
                        GuessHintsScreen(innerPadding)
                    }
                }
            }
        }
    }
}

@Composable
fun GuessHintsScreen(innerPadding: PaddingValues) {
    val (countries, countryKeys, countryValues) = countryKeyValues()
    val isCorrect = rememberSaveable { mutableStateOf(false) }
    var openAlertDialog = rememberSaveable { mutableStateOf(false) }
    val nextRound = rememberSaveable { mutableStateOf(false) }

    val random = rememberSaveable { mutableStateOf(countryKeys.random()) }
    val input = rememberSaveable { mutableStateOf("") }
    val guesses = rememberSaveable { mutableSetOf<Char>() }
    val dashes =
        rememberSaveable {
            mutableStateOf(
                (countries[random.value]
                    .toString()
                    .replace(Regex("[^\\w\\s]"), "_"))
            )
        }
    containsChar(countries[random.value] as String, ' ', dashes, guesses)
    var incorrectCount by remember { mutableIntStateOf(0) }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(innerPadding)
            .padding(16.dp)
            .fillMaxHeight()
    ) {
        when {
            openAlertDialog.value -> {
                CheckAnswerDialog(
                    onDismissRequest = {
                        openAlertDialog.value = false
                        incorrectCount = 0
                        isCorrect.value = false
                    },
                    dialogStatus = isCorrect.value,
                    country = countries[random.value].toString(),
                )
            }
        }
        Text(countries[random.value].toString())
        FlagImage(flagByCountryCode(random.value))
        Text(
            dashes.value,
            fontFamily = FontFamily.Monospace,
            fontSize = 24.sp,
            modifier = Modifier.padding(30.dp),
            textAlign = TextAlign.Center
        )
        OutlinedTextField(
            value = input.value,
            onValueChange = { newValue ->
                input.value = newValue.take(1) // Limit to max 1 character
            },
            label = { Text("Type a character") },
            modifier = Modifier.fillMaxWidth(),
        )
        Button(onClick = {
            val contains = containsChar(
                countries[random.value] as String,
                if (input.value.isNotEmpty()) input.value[0] else ' ',
                dashes,
                guesses
            )
            input.value = "" // Reset input
            if (!contains) incorrectCount++
        }) {
            Text("Check letter")
        }
        Text("Incorrect guesses: $incorrectCount")
        if (incorrectCount >= 3) {
            openAlertDialog.value = true
            nextRound.value = true
        }
        if (!dashes.value.contains('_') && !nextRound.value) {
            openAlertDialog.value = true
            nextRound.value = true
            isCorrect.value = true
        }
        // TODO: Clear guesses
//        ActionButton(nextRound, random, countryKeys, openAlertDialog, isCorrect)
    }
}

fun containsChar(
    word: String,
    char: Char,
    dashes: MutableState<String>,
    guesses: MutableSet<Char>
): Boolean {
    var contains = false
    if (word.contains(char, ignoreCase = true)) {
        guesses.add(char)
        contains = true
    }
    dashes.value =
        word.map { if (guesses.contains(it.lowercaseChar())) it else '_' }
            .joinToString(" ") + " "
    return contains
}
