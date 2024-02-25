package com.hashankur.countryflags

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hashankur.countryflags.ui.theme.CountryFlagsTheme

class MainActivity : ComponentActivity() {
    enum class Routes() {
        GUESS_COUNTRY,
        GUESS_HINTS,
        GUESS_FLAG,
        ADVANCED_LEVEL
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountryFlagsTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            title = {
                                Text(getString(R.string.app_name))
                            },
                        )
                    },
                ) { innerPadding ->
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Menu()
                    }
                }
            }
        }
    }
}

@Composable
fun MenuButton(name: String, onClick: () -> Unit) {
    Button(
        onClick = {},
        Modifier.width(250.dp)
    )
    {
        Text(name)
    }
}

@Composable
fun Menu() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MenuButton("Guess the Country", onClick = { /*TODO*/ })
        MenuButton("Guess Hints", onClick = { /*TODO*/ })
        MenuButton("Guess the Flag", onClick = { /*TODO*/ })
        MenuButton("Advanced Level", onClick = { /*TODO*/ })
    }
}

//fun readJSON() {
//    val text = this.getResources().openRawResource(R.raw.countries)
//        .bufferedReader().use { it.readText() }
//    var num = 0
//    num++
//}
//val navController = rememberNavController()
