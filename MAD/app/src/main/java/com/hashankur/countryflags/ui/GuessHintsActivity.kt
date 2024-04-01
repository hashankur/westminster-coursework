package com.hashankur.countryflags.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
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
import com.hashankur.countryflags.containsChar
import com.hashankur.countryflags.countryKeyValues
import com.hashankur.countryflags.flagByCountryCode
import com.hashankur.countryflags.ui.components.CheckAnswerDialog
import com.hashankur.countryflags.ui.components.GoToNextLevel
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
                    val (countries, countryKeys, countryValues) = countryKeyValues()
                    var isCorrect by rememberSaveable { mutableStateOf(false) }
                    var openAlertDialog by rememberSaveable { mutableStateOf(false) }

                    var random by rememberSaveable { mutableStateOf(countryKeys.random()) }
                    var input by rememberSaveable { mutableStateOf("") }
                    val guesses = rememberSaveable { mutableSetOf<Char>() }
                    val dashes =
                        rememberSaveable {
                            mutableStateOf(
                                (countries[random]
                                    .toString()
                                    .replace(Regex("[^\\w\\s]"), "_"))
                            )
                        }
                    containsChar(countries[random] as String, ' ', dashes, guesses)
                    var incorrectCount by remember { mutableIntStateOf(0) }

                    var nextRound by rememberSaveable { mutableStateOf(false) }

                    Scaffold(
                        topBar = {
                            TopBarBuilder(
                                getString(R.string.mode2), goBack = { finish() }
                            )
                        },
                        floatingActionButton = {
                            GoToNextLevel(nextRound, action = { nextRound = !nextRound })
                        },
                    ) { innerPadding ->
                        LaunchedEffect(nextRound) {
                            if (nextRound) openAlertDialog = true
                            else {
                                random = countryKeys.random()
                                isCorrect = false
                            }
                        }

                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(innerPadding)
                                .padding(16.dp)
                                .fillMaxHeight()
                        ) {
                            when {
                                openAlertDialog -> {
                                    CheckAnswerDialog(
                                        onDismissRequest = {
                                            openAlertDialog = false
                                            incorrectCount = 0
                                            isCorrect = false
                                        },
                                        dialogStatus = isCorrect,
                                        country = countries[random].toString(),
                                    )
                                }
                            }
                            Text(countries[random].toString())
                            FlagImage(flagByCountryCode(random))
                            Text(
                                dashes.value,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 24.sp,
                                modifier = Modifier.padding(30.dp),
                                textAlign = TextAlign.Center
                            )
                            Row() {
                                OutlinedTextField(
                                    value = input,
                                    onValueChange = { newValue ->
                                        input = newValue.take(1) // Limit to max 1 character
                                    },
                                    label = { Text("Type a character") },
                                    modifier = Modifier.fillMaxWidth(0.7f),
                                )
                                // Spacer(modifier = Modifier.weight(1f))
                                Button(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(60.dp)
                                        .padding(horizontal = 10.dp),
                                    onClick = {
                                        val contains = containsChar(
                                            countries[random] as String,
                                            if (input.isNotEmpty()) input[0] else ' ',
                                            dashes,
                                            guesses
                                        )
                                        input = "" // Reset input
                                        if (!contains) incorrectCount++
                                    }) {
                                    Text("Submit", textAlign = TextAlign.Center)
                                }
                            }
                            Text("Incorrect guesses: $incorrectCount")
                            if (incorrectCount >= 3) {
                                openAlertDialog = true
                                nextRound = true
                            }
                            if (!dashes.value.contains('_') && !nextRound) {
                                openAlertDialog = true
                                nextRound = true
                                isCorrect = true
                            }
                            // TODO: Clear guesses
                        }
                    }
                }
            }
        }
    }
}
