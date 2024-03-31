package com.hashankur.countryflags

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.hashankur.countryflags.ui.GuessAdvancedActivity
import com.hashankur.countryflags.ui.GuessCountryActivity
import com.hashankur.countryflags.ui.GuessFlagActivity
import com.hashankur.countryflags.ui.GuessHintsActivity
import com.hashankur.countryflags.ui.components.MenuScreen
import com.hashankur.countryflags.ui.theme.CountryFlagsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountryFlagsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
                    ) { innerPadding -> // Padding for top bar
                        val context = LocalContext.current
                        MenuScreen(
                            innerPadding,
                            onNavigateToGuessCountry = {
                                navigateToActivity(
                                    context,
                                    GuessCountryActivity::class.java
                                )
                            },
                            onNavigateToGuessHints = {
                                navigateToActivity(
                                    context,
                                    GuessHintsActivity::class.java
                                )
                            },
                            onNavigateToGuessFlag = {
                                navigateToActivity(
                                    context,
                                    GuessFlagActivity::class.java
                                )
                            },
                            onNavigateToAdvancedLevel = {
                                navigateToActivity(
                                    context,
                                    GuessAdvancedActivity::class.java
                                )
                            },
                        )
                    }
                }
            }
        }
    }
}

fun navigateToActivity(context: Context, activity: Class<*>) {
    Intent(context, activity).also { context.startActivity(it) }
}

