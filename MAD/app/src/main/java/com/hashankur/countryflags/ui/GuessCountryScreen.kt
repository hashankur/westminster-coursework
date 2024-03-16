package com.hashankur.countryflags.ui

import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.io.InputStream
import java.util.Locale

@Composable
fun GuessCountryScreen() {
    val countries = readJSON()
    val random = countries.keys.random()

    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
        Text(countries[random].toString())
        DisplayFlagByCountryCode(countryCode = random)
        LazyColumn {
            items(countries.size) {
                val country = countries.values.elementAt(it)
                ListItem(
                    headlineContent = { Text(country) },
                )
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun DisplayFlagByCountryCode(countryCode: String) {
    val context = LocalContext.current
    val resources: Resources = context.resources
    val drawableId = resources.getIdentifier(
        countryCode.lowercase(Locale.ROOT),
        "drawable",
        context.packageName
    )

    if (drawableId != 0) {
        Image(
            painter = painterResource(drawableId),
            contentDescription = "Flag of $countryCode",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.width(maxOf(IntrinsicSize.Max))
//                .aspectRatio(16f / 9f)
        )
    } else {
        // Handle cases where the flag image is not found (optional)
    }
}

@Composable
fun readJSON(): Map<String, String> {
    val context = LocalContext.current
    val inputStream: InputStream = context.assets.open("countries.json")
    val size: Int = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
//    String(buffer)
    return parseSimpleJSONToMap(String(buffer))
}

fun parseSimpleJSONToMap(jsonString: String): Map<String, String> {
    val resultMap = mutableMapOf<String, String>()
    val trimmedJson = jsonString.trim().replace("\\s", "") // Remove whitespace

    // Split based on commas, assuming no commas within values (limited functionality)
    val keyValuePairs = trimmedJson.substring(1, trimmedJson.length - 2).split(",\n")
    Log.d("keyValuePairs", keyValuePairs.toString())

    for (pair in keyValuePairs) {
        val parts = pair.split(":")
        Log.d("parts", parts.toString())
        // Basic handling, assuming keys and values are always within quotes (limited)
        val key = parts[0].substring(3, parts[0].length - 1).trim()
        Log.d("key", key)
        val value = parts[1].substring(2, parts[1].length - 1).trim()
        Log.d("value", value)
        resultMap[key] = value
    }

    return resultMap
}
