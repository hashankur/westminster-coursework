package com.hashankur.countryflags

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hashankur.countryflags.ui.AdvancedLevelScreen
import com.hashankur.countryflags.ui.GuessCountryScreen
import com.hashankur.countryflags.ui.GuessFlagScreen
import com.hashankur.countryflags.ui.GuessHintsScreen
import com.hashankur.countryflags.ui.MenuScreen
import com.hashankur.countryflags.ui.theme.CountryFlagsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
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
                ) { innerPadding -> // Padding for top bar
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        // https://developer.android.com/guide/navigation/use-graph/navigate
                        NavHost(navController, startDestination = "home") {
                            composable(route = "home") {
                                MenuScreen(
                                    onNavigateToGuessCountry = { navController.navigate("guess_country") },
                                    onNavigateToGuessHints = { navController.navigate("guess_hints") },
                                    onNavigateToGuessFlag = { navController.navigate("guess_flag") },
                                    onNavigateToAdvancedLevel = { navController.navigate("advanced_level") }
                                )
                            }
                            composable(route = "guess_country") {
                                GuessCountryScreen()
                            }
                            composable(route = "guess_hints") {
                                GuessHintsScreen()
                            }
                            composable(route = "guess_flag") {
                                GuessFlagScreen()
                            }
                            composable(route = "advanced_level") {
                                AdvancedLevelScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}
