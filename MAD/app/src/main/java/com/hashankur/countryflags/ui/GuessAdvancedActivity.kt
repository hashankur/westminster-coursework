package com.hashankur.countryflags.ui

import android.os.Bundle
import android.util.Log
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
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hashankur.countryflags.FlagImage
import com.hashankur.countryflags.R
import com.hashankur.countryflags.countdown
import com.hashankur.countryflags.countryKeyValues
import com.hashankur.countryflags.flagByCountryCode
import com.hashankur.countryflags.ui.components.CheckAnswerDialog
import com.hashankur.countryflags.ui.components.GoToNextLevel
import com.hashankur.countryflags.ui.components.TopBarBuilder
import com.hashankur.countryflags.ui.theme.CountryFlagsTheme

class GuessAdvancedActivity : ComponentActivity() {
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

                    var displayedCountries by rememberSaveable { mutableStateOf((1..3).map { countryKeys.random() }) }
                    var openAlertDialog by rememberSaveable { mutableStateOf(false) }

                    var nextRound by rememberSaveable { mutableStateOf(false) }
                    var count by remember { mutableIntStateOf(0) }

                    var isCorrect = mutableListOf(false, false, false)
                    val status = rememberSaveable { mutableListOf(false, false, false) }
                    var firstRun by rememberSaveable { mutableStateOf(false) }

                    var country1 by rememberSaveable { mutableStateOf(false) }
                    var country2 by rememberSaveable { mutableStateOf(false) }
                    var country3 by rememberSaveable { mutableStateOf(false) }

                    Scaffold(
                        topBar = {
                            TopBarBuilder(
                                getString(R.string.mode4),
                                goBack = { finish() },
                                timer,
                                countdown(nextRound).first
                            )
                        },
                        floatingActionButton = {
                            GoToNextLevel(nextRound, action = {
                                if (!firstRun) firstRun = true
                                isCorrect = status
                                if (nextRound) {
                                    displayedCountries = (1..3).map { countryKeys.random() }
                                    count = -1
                                    nextRound = false
                                    isCorrect.fill(false)
                                }
                                count++
                                if (count >= 3) {
                                    nextRound = true
                                    Log.d("test", "test")
                                }
                                if (count == 3)
                                    openAlertDialog = true
                            })
                        },
                    ) { innerPadding ->
                        when {
                            openAlertDialog -> {
                                CheckAnswerDialog(
                                    onDismissRequest = {
                                        openAlertDialog = false
                                        isCorrect.fill(false)
                                    },
                                    dialogStatus = !status.contains(false),
                                )
                            }
                        }

                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(innerPadding)
                                .padding(horizontal = 80.dp)
                                .padding(top = 30.dp)
                                .fillMaxHeight()
                                .verticalScroll(rememberScrollState())
                        ) {
                            Text("Attempts remaining: " + (3 - count))
                            Spacer(Modifier.padding(10.dp))
                            displayedCountries.withIndex().forEach { (index, country) ->
                                var input by remember { mutableStateOf("") }
                                LaunchedEffect(displayedCountries) {
                                    input = ""  // Clear text inputs
                                }

                                FlagImage(flagByCountryCode(country))
                                Text(countries[country].toString())
                                OutlinedTextField(
                                    value = input,
                                    onValueChange = {
                                        input = it
                                        val equals =
                                            input.lowercase() == countries[country].toString()
                                                .lowercase()
                                        status[index] = equals
                                        when (index) {
                                            0 -> country1 = equals
                                            1 -> country2 = equals
                                            2 -> country3 = equals
                                        }
                                    },
                                    label = { Text("Guess country name") },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 30.dp),
                                    enabled = !isCorrect[index],
                                    isError = input != "" && !isCorrect[index] && firstRun && !nextRound,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        disabledTextColor = Color.Green,
                                        errorTextColor = Color.Red
                                    )
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}