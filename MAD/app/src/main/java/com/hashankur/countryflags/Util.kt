package com.hashankur.countryflags

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
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
fun FlagImage(resource: Pair<Int, String>, modifier: Modifier = Modifier) {
    val (drawableId, countryCode) = resource
    Image(
        painter = painterResource(drawableId),
        contentDescription = "Flag of $countryCode",
        contentScale = ContentScale.Fit,
        modifier = modifier
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

fun containsChar(
    word: String,
    char: Char,
    dashes: MutableState<String>,
    guesses: MutableSet<Char>
): Boolean {
    var contains = false
    if (word.contains(char, ignoreCase = true)) {
        guesses.add(char)
        contains = true
    }
    dashes.value =
        word.map { if (guesses.contains(it.lowercaseChar())) it else '_' }
            .joinToString(" ") + " "
    return contains
}

// https://stackoverflow.com/questions/71191340/how-can-i-implement-a-timer-in-a-portable-way-in-jetpack-compose
@Composable
fun countdown(reset: Boolean): Pair<Long, Boolean> {
    var remainingTime by remember { mutableLongStateOf(10000L) } // Initial time in milliseconds
    LaunchedEffect(reset) { // Launch only once
        val startTime = withFrameMillis { it }
        while (remainingTime > 0) {
            val currentTime = withFrameMillis { it }
            val elapsedTime = currentTime - startTime
            remainingTime = 10000L - elapsedTime
            delay(1000L) // Update every second
        }
    }
    return Pair(remainingTime, remainingTime.toInt() == 0)
}