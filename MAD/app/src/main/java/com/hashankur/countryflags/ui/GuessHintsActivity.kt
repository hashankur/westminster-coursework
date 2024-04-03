package com.hashankur.countryflags.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.hashankur.countryflags.countdown
import com.hashankur.countryflags.countryKeyValues
import com.hashankur.countryflags.flagByCountryCode
import com.hashankur.countryflags.ui.components.CheckAnswerDialog
import com.hashankur.countryflags.ui.components.GoToNextLevel
import com.hashankur.countryflags.ui.components.TopBarBuilder
import com.hashankur.countryflags.ui.theme.CountryFlagsTheme

class GuessHintsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val timer = intent.getBooleanExtra("timer", false)
        setContent {
            CountryFlagsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val (countries, countryKeys, _) = countryKeyValues()
                    var isCorrect by rememberSaveable { mutableStateOf(false) }
                    var openAlertDialog by rememberSaveable { mutableStateOf(false) }

                    var random by rememberSaveable { mutableStateOf(countryKeys.random()) }
                    var input by rememberSaveable { mutableStateOf("") }
                    val guesses = rememberSaveable { mutableSetOf<Char>() }
                    var dashes =
                        rememberSaveable {
                            mutableStateOf(
                                countries[random]
                                    .toString()
                                    .replace(Regex("[^\\w\\s]"), "_")
                            )
                        }
                    containsChar(countries[random] as String, ' ', dashes, guesses)
                    var incorrectCount by remember { mutableIntStateOf(0) }

                    var nextRound by rememberSaveable { mutableStateOf(false) }

                    Scaffold(
                        topBar = {
                            TopBarBuilder(
                                getString(R.string.mode2),
                                goBack = { finish() },
                                timer,
                                countdown(nextRound).first
                            )
                        },
                        floatingActionButton = {
                            GoToNextLevel(nextRound, action = {
                                if (nextRound) {
                                    random = countryKeys.random()
                                    guesses.clear()
                                    nextRound = false
                                    incorrectCount = -1
                                }
                                val contains = containsChar(
                                    countries[random] as String,
                                    if (input.isNotEmpty()) input[0] else ' ',
                                    dashes,
                                    guesses
                                )
                                input = "" // Reset input
                                if (!contains) incorrectCount++

                                if (!dashes.value.contains('_')) {
                                    openAlertDialog = true
                                    nextRound = true
                                    isCorrect = true
                                }

                                if (incorrectCount >= 3) {
                                    openAlertDialog = true
                                    nextRound = true
                                }

                            })
                        },
                    ) { innerPadding ->
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(innerPadding)
                                .padding(16.dp)
                                .fillMaxHeight()
                                .verticalScroll(rememberScrollState())
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
                            FlagImage(flagByCountryCode(random))
                            Text(
                                dashes.value,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 24.sp,
                                modifier = Modifier
                                    .padding(30.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                lineHeight = 80.sp
                            )
                            OutlinedTextField(
                                value = input,
                                onValueChange = { newValue ->
                                    input = newValue.take(1) // Limit to max 1 character
                                },
                                label = { Text("Type a character") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                            Text("Incorrect guesses: $incorrectCount")
                        }
                    }
                }
            }
        }
    }
}
