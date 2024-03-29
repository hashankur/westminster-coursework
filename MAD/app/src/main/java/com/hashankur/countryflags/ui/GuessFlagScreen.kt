package com.hashankur.countryflags.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hashankur.countryflags.FlagImage
import com.hashankur.countryflags.countryKeyValues
import com.hashankur.countryflags.flagByCountryCode

@Composable
fun GuessFlagScreen() {
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
