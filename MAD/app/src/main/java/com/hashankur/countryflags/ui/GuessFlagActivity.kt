package com.hashankur.countryflags.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hashankur.countryflags.FlagImage
import com.hashankur.countryflags.R
import com.hashankur.countryflags.countdown
import com.hashankur.countryflags.countryKeyValues
import com.hashankur.countryflags.flagByCountryCode
import com.hashankur.countryflags.ui.components.CheckAnswerDialog
import com.hashankur.countryflags.ui.components.GoToNextLevel
import com.hashankur.countryflags.ui.components.TopBarBuilder
import com.hashankur.countryflags.ui.theme.CountryFlagsTheme

class GuessFlagActivity : ComponentActivity() {
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
                    var random by rememberSaveable { mutableStateOf(displayedCountries.random()) }
                    var isCorrect by rememberSaveable { mutableStateOf(false) }
                    var openAlertDialog by rememberSaveable { mutableStateOf(false) }

                    val nextRound by rememberSaveable { mutableStateOf(true) }
                    var attempt by remember { mutableStateOf(false) }

                    Scaffold(
                        topBar = {
                            TopBarBuilder(
                                getString(R.string.mode3),
                                goBack = { finish() },
                                timer,
                                countdown(nextRound).first
                            )
                        },
                        floatingActionButton = {
                            GoToNextLevel(nextRound, action = {
                                displayedCountries = (1..3).map { countryKeys.random() }
                                random = displayedCountries.random()
                                isCorrect = false
                                attempt = false
                            })
                        },
                    ) { innerPadding ->
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(innerPadding)
                                .padding(horizontal = 50.dp)
                                .padding(top = 30.dp)
                                .fillMaxHeight()
                                .verticalScroll(rememberScrollState())
                        ) {
                            when {
                                openAlertDialog -> {
                                    CheckAnswerDialog(
                                        onDismissRequest = { openAlertDialog = false },
                                        dialogStatus = isCorrect,
                                        country = countries[random].toString(),
                                    )
                                }
                            }

                            Text(
                                countries[random].toString(),
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 40.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 30.dp)
                            )

                            displayedCountries.forEach { country ->
                                FlagImage(
                                    flagByCountryCode(country),
                                    modifier = Modifier.clickable {
                                        if (random == country)
                                            isCorrect = true
                                        if (!attempt) openAlertDialog = true
                                        attempt = true
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}