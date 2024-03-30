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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hashankur.countryflags.FlagImage
import com.hashankur.countryflags.countryKeyValues
import com.hashankur.countryflags.flagByCountryCode
import com.hashankur.countryflags.ui.theme.CountryFlagsTheme

class GuessFlagActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountryFlagsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val (countries, countryKeys, countryValues) = countryKeyValues()

                    val displayedCountries = (1..3).map { countryKeys.random() }

                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .fillMaxHeight()
                    ) {
                        (1..3).forEach { index ->
                            FlagImage(flagByCountryCode(displayedCountries[index - 1]))
                            Spacer(modifier = Modifier.padding(0.dp))
                        }
                        Text(countries[displayedCountries.random()].toString())
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Submit")
                        }
                    }
                }
            }
        }
    }
}