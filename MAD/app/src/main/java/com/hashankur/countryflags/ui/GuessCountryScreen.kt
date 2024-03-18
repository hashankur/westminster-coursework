package com.hashankur.countryflags.ui

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import org.json.JSONObject
import java.io.InputStream

@Composable
fun GuessCountryScreen() {
    val countries = readJSON()
    val countryKeys = countries.keys().asSequence().toList()
    val countryValues = countryKeys.map { countries[it] as String }.toList().sorted()

    // TODO: do not repeat the same country
    val random = rememberSaveable { mutableStateOf(countryKeys.random()) }
    val isCorrect = rememberSaveable { mutableStateOf(false) }
    val openAlertDialog = rememberSaveable { mutableStateOf(false) }
    val nextRound = rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .fillMaxHeight()
    ) {
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
        LazyColumn(Modifier.weight(1f)) {
            items(countries.length()) {
                val country = countryValues.elementAt(it)
                ListItem(
                    headlineContent = { Text(country) },
                    Modifier.clickable { isCorrect.value = (country == countries[random.value]) }
                )
                HorizontalDivider()
            }
        }
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            ActionButton(nextRound, random, countryKeys, openAlertDialog)
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
        countryCode.lowercase().replace("-", "_"), // drawables cannot have hyphens
        "drawable",
        context.packageName
    )

    if (drawableId != 0) {
        Image(
            painter = painterResource(drawableId),
            contentDescription = "Flag of $countryCode",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.aspectRatio(16f / 9f)
        )
    } else {
        // Handle cases where the flag image is not found (optional)
    }
}

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
fun ActionButton(
    nextRound: MutableState<Boolean>,
    random: MutableState<String>,
    countryKeys: List<String>,
    openAlertDialog: MutableState<Boolean>
) {
    if (nextRound.value) {
        FilledTonalButton(
            onClick = {
                random.value = countryKeys.random(); nextRound.value = !nextRound.value
            }) {
            Text(text = "Next")
            Spacer(Modifier.size(ButtonDefaults.IconSize))
            Icon(Icons.AutoMirrored.Filled.ArrowForward, "Next Button")
        }

    } else {
        Button(
            onClick = { openAlertDialog.value = true }) {
            Text(text = "Submit")
            Spacer(Modifier.size(ButtonDefaults.IconSize))
            Icon(Icons.Filled.Check, "Submit Button")
        }
    }
}