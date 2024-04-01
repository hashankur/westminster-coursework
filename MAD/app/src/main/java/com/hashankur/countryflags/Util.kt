package com.hashankur.countryflags

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.json.JSONObject
import java.io.InputStream

@Composable
fun readJSON(): JSONObject {
    val context = LocalContext.current
    val inputStream: InputStream = context.assets.open("countries.json")
    val size: Int = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    return JSONObject(String(buffer))
}

@Composable
fun flagByCountryCode(countryCode: String): Pair<Int, String> {
    val context = LocalContext.current
    val resources: Resources = context.resources
    return Pair(
        resources.getIdentifier(
            countryCode.lowercase().replace("-", "_"), // drawables cannot have hyphens
            "drawable",
            context.packageName
        ), countryCode
    )
}

@Composable
fun FlagImage(resource: Pair<Int, String>) {
    val (drawableId, countryCode) = resource
    Image(
        painter = painterResource(drawableId),
        contentDescription = "Flag of $countryCode",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .aspectRatio(16f / 9f)
            .padding(bottom = 10.dp)
    )
}

@Composable
fun countryKeyValues(): Triple<JSONObject, List<String>, List<String>> {
    val countries = readJSON()
    val countryKeys = countries.keys().asSequence().toList()
    val countryValues = countryKeys.map { countries[it] as String }.toList().sorted()
    return Triple(countries, countryKeys, countryValues)
}

@Composable
fun ActionButton(
    nextRound: Boolean,
    submit: () -> Unit,
    next: () -> Unit,
) {
    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
        // TODO: Use a single button
        if (nextRound) {
            FilledTonalButton(onClick = { next() }) {
                Text(text = "Next")
                Spacer(Modifier.size(ButtonDefaults.IconSize))
                Icon(Icons.AutoMirrored.Filled.ArrowForward, "Next")
            }

        } else {
            Button(onClick = { submit() }) {
                Text(text = "Submit")
                Spacer(Modifier.size(ButtonDefaults.IconSize))
                Icon(Icons.Filled.Check, "Submit")
            }
        }
    }
}
