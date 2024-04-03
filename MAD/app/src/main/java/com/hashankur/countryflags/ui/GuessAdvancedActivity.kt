package com.hashankur.countryflags.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hashankur.countryflags.FlagImage
import com.hashankur.countryflags.R
import com.hashankur.countryflags.countryKeyValues
import com.hashankur.countryflags.flagByCountryCode
import com.hashankur.countryflags.ui.components.CheckAnswerDialog
import com.hashankur.countryflags.ui.components.GoToNextLevel
import com.hashankur.countryflags.ui.components.TopBarBuilder
import com.hashankur.countryflags.ui.theme.CountryFlagsTheme

class GuessAdvancedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                    var attempt by remember { mutableStateOf(false) }
                    var count by remember { mutableIntStateOf(0) }

                    var isCorrect = mutableListOf(false, false, false)
                    var status = rememberSaveable { mutableListOf(false, false, false) }

                    var country1 by rememberSaveable { mutableStateOf(false) }
                    var country2 by rememberSaveable { mutableStateOf(false) }
                    var country3 by rememberSaveable { mutableStateOf(false) }

//                    var input by rememberSaveable { mutableStateOf(listOf<Boolean>()) }
//                    var input = remember { mutableMapOf<String, String>() }

                    Scaffold(
                        topBar = {
                            TopBarBuilder(
                                getString(R.string.mode3), goBack = { finish() }
                            )
                        },
                        floatingActionButton = {
                            GoToNextLevel(nextRound, action = {
//                                var didChange = (isCorrect != status)
                                isCorrect = status
                                if (nextRound) {
                                    displayedCountries = (1..3).map { countryKeys.random() }
                                    count = -1
                                    nextRound = false
                                }
//                                isCorrect = false
//                                attempt = false
//                                if (!didChange)
                                count++
//                                Log.d("test2", didChange.toString())
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
//                                        incorrectCount = 0
                                        isCorrect.fill(false)
                                    },
                                    dialogStatus = !isCorrect.contains(false),
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
                        ) {
                            Text("Status: $country1, $country2, $country3")
//                            Text("Status: $status")
//                            Text("Correct: $isCorrect")
                            Text("Count: $count")
                            displayedCountries.withIndex().forEach { (index, country) ->
                                var input by remember { mutableStateOf("") }
//                                var isCorrect by rememberSaveable { mutableStateOf(false) }

                                FlagImage(flagByCountryCode(country))
                                Text(countries[country].toString())
                                OutlinedTextField(
                                    value = input,
                                    onValueChange = {
                                        input = it
//                                        status[index] =
                                        var equals =
                                            input.lowercase() == countries[country].toString()
                                                .lowercase()
                                        when (index) {
                                            0 -> country1 = equals
                                            1 -> country2 = equals
                                            2 -> country3 = equals
                                        }
//                                        input[country] = input[country] + it
//                                        Log.d("it", it.toString())
//                                        Log.d("input", input.toString())
                                    },
                                    label = { Text("Guess country name") },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 30.dp),
                                    enabled = !isCorrect[index],
                                    colors = OutlinedTextFieldDefaults.colors(
                                        disabledTextColor = Color.Green

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