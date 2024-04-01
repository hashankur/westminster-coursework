package com.hashankur.countryflags.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hashankur.countryflags.FlagImage
import com.hashankur.countryflags.R
import com.hashankur.countryflags.countryKeyValues
import com.hashankur.countryflags.flagByCountryCode
import com.hashankur.countryflags.ui.components.CheckAnswerDialog
import com.hashankur.countryflags.ui.components.GoToNextLevel
import com.hashankur.countryflags.ui.components.TopBarBuilder
import com.hashankur.countryflags.ui.theme.CountryFlagsTheme

class GuessCountryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountryFlagsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val (countries, countryKeys, countryValues) = countryKeyValues()

                    // TODO: do not repeat the same country
                    var random by rememberSaveable { mutableStateOf(countryKeys.random()) }
                    var isCorrect by rememberSaveable { mutableStateOf(false) }
                    var openAlertDialog by rememberSaveable { mutableStateOf(false) }

                    var nextRound by rememberSaveable { mutableStateOf(false) }

                    Scaffold(
                        topBar = {
                            TopBarBuilder(
                                getString(R.string.mode1), goBack = { finish() }
                            )
                        },
                        floatingActionButton = {
                            GoToNextLevel(nextRound, action = { nextRound = !nextRound })
                        },
                    ) { innerPadding ->
                        // TODO: Fix double calculation
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
                            Log.d("random", random)
                            when {
                                openAlertDialog -> {
                                    CheckAnswerDialog(
                                        onDismissRequest = { openAlertDialog = false },
                                        dialogStatus = isCorrect,
                                        country = countries[random].toString(),
                                    )
                                }
                            }
                            // Text(countries[random.value].toString())
                            FlagImage(flagByCountryCode(random))

                            // var selectedIndex by remember { mutableStateOf(-1) }

                            LazyColumn() {
                                items(countries.length()) {
                                    val country = countryValues.elementAt(it)
                                    ListItem(
                                        headlineContent = { Text(country) },
                                        Modifier
                                            .clickable {
                                                isCorrect = (country == countries[random])
//                            selectedIndex = it
//                            Log.d("it", country.toString())
//                            Log.d("selected", selectedIndex.toString())
//                            Log.d("comp", (it == selectedIndex).toString())
                                            }
//                        .background(
//                            if (it == selectedIndex) Color.Green else Color.Red
//                        )
                                    )
                                    HorizontalDivider()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}