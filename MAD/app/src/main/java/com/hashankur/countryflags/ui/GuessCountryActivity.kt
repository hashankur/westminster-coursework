package com.hashankur.countryflags.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.hashankur.countryflags.FlagImage
import com.hashankur.countryflags.R
import com.hashankur.countryflags.countryKeyValues
import com.hashankur.countryflags.flagByCountryCode
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
                    var nextRound by rememberSaveable { mutableStateOf(false) }

                    Scaffold(
                        topBar = {
                            TopBarBuilder(
                                getString(R.string.mode1), goBack = { finish() }
                            )
                        },
                        floatingActionButton = {
                            ExtendedFloatingActionButton(
                                text = { if (!nextRound) Text("Submit") else Text("Next") },
                                icon = {
                                    if (!nextRound) Icon(Icons.Filled.Check, "Submit")
                                    else Icon(
                                        Icons.AutoMirrored.Filled.ArrowForward,
                                        "Next"
                                    )
                                },
                                onClick = { nextRound = !nextRound },
                                containerColor = if (nextRound) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
                            )
                        },
                    ) { innerPadding ->
                        GuessCountryScreen(innerPadding, nextRound)
                    }
                }
            }
        }
    }
}

@Composable
fun GuessCountryScreen(innerPadding: PaddingValues, nextRound: Boolean) {
    val (countries, countryKeys, countryValues) = countryKeyValues()

    // TODO: do not repeat the same country
    var random by rememberSaveable { mutableStateOf(countryKeys.random()) }
    var isCorrect by rememberSaveable { mutableStateOf(false) }
    var openAlertDialog by rememberSaveable { mutableStateOf(false) }

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

@Composable
fun CheckAnswerDialog(
    onDismissRequest: () -> Unit,
    dialogStatus: Boolean,
    country: String,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = if (dialogStatus) "CORRECT!" else "WRONG!",
                    modifier = Modifier.padding(16.dp),
                    color = if (dialogStatus) Color(0xFF1C6B50) else Color(0xFFFF5A39),
                    fontWeight = FontWeight.Bold,
                    fontSize = 35.sp
                )
                Text(
                    text = country,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                    color = Color(0xFF4285F4),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
