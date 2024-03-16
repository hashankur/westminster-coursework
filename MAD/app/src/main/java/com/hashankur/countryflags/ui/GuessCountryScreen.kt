package com.hashankur.countryflags.ui

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.io.InputStream
import java.util.Locale

@Composable
fun GuessCountryScreen() {
    val countries = readJSON()
    val random = rememberSaveable { mutableStateOf(countries.keys.random()) }
    val isCorrect = rememberSaveable { mutableStateOf(false) }
    val openAlertDialog = rememberSaveable { mutableStateOf(false) }
    val nextRound = rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        if (nextRound.value) {
            ExtendedFloatingActionButton(
                onClick = {
                    random.value = countries.keys.random(); nextRound.value = !nextRound.value
                },
                icon = { Icon(Icons.AutoMirrored.Filled.ArrowForward, "Next Button") },
                text = { Text(text = "Next") },
            )
        } else {
            ExtendedFloatingActionButton(
                onClick = { openAlertDialog.value = true },
                icon = { Icon(Icons.Filled.Check, "Submit Button") },
                text = { Text(text = "Submit") },
            )
        }
        when {
            openAlertDialog.value -> {
                CheckAnswerDialog(
                    onDismissRequest = { openAlertDialog.value = false; nextRound.value = true },
                    dialogStatus = isCorrect.value,
                    country = countries[random.value].toString(),
                    icon = Icons.Default.Info
                )
            }
        }
        Text(countries[random.value].toString())
        DisplayFlagByCountryCode(countryCode = random.value)
        LazyColumn {
            items(countries.size) {
                val country = countries.values.sorted().elementAt(it)
                ListItem(
                    headlineContent = { Text(country) },
                    Modifier.clickable { isCorrect.value = (country == countries[random.value]) }
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
    icon: ImageVector
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
                Icon(icon, contentDescription = "Example Icon")
                Text(
                    text = if (dialogStatus) "CORRECT!" else "INCORRECT!",
                    modifier = Modifier.padding(16.dp),
                    color = if (dialogStatus) Color.Green else Color.Red
                )
                Text(
                    text = country,
                    modifier = Modifier.padding(16.dp),
                )
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
    return jsonToMap(String(buffer))
}

fun jsonToMap(jsonString: String): Map<String, String> {
    val resultMap = mutableMapOf<String, String>()
    val trimmedJson = jsonString.trim().replace("\\s", "") // Remove whitespace

    // Split based on commas, assuming no commas within values (limited functionality)
    val keyValuePairs = trimmedJson.substring(1, trimmedJson.length - 2).split(",\n")

    for (pair in keyValuePairs) {
        val parts = pair.split(":")
        // Basic handling, assuming keys and values are always within quotes (limited)
        val key = parts[0].substring(3, parts[0].length - 1).trim()
        val value = parts[1].substring(2, parts[1].length - 1).trim()
        resultMap[key] = value
    }

    return resultMap
}
