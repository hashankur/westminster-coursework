package com.hashankur.countryflags

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hashankur.countryflags.ui.GuessAdvancedActivity
import com.hashankur.countryflags.ui.GuessCountryActivity
import com.hashankur.countryflags.ui.GuessFlagActivity
import com.hashankur.countryflags.ui.GuessHintsActivity
import com.hashankur.countryflags.ui.components.MenuScreen
import com.hashankur.countryflags.ui.theme.CountryFlagsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val currentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route
            CountryFlagsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            when (currentRoute) {
                                "guess_country" -> {
                                    TopBarBuilder(title = "Guess Country", navController)
                                }

                                "guess_hints" -> {
                                    TopBarBuilder(title = "Guess Hints", navController)
                                }

                                "guess_flag" -> {
                                    TopBarBuilder(title = "Guess Flag", navController)
                                }

                                "advanced_level" -> {
                                    TopBarBuilder(title = "Advanced Level", navController)
                                }

                                else -> {
                                    CenterAlignedTopAppBar(
                                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                            containerColor = MaterialTheme.colorScheme.primary,
                                            titleContentColor = MaterialTheme.colorScheme.onPrimary
                                        ),
                                        title = {
                                            Text(getString(R.string.app_name))
                                        },
                                    )
                                }
                            }
                        },
                    ) { innerPadding -> // Padding for top bar
                        val context = LocalContext.current
                        MenuScreen(
                            modifier = Modifier.padding(innerPadding),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarBuilder(title: String, navController: NavHostController) {
    TopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text(title)
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Go back",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    )
}

fun navigateToActivity(context: Context, activity: Class<*>) {
    Intent(context, activity).also { context.startActivity(it) }
}

