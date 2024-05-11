// Link to video: https://drive.google.com/drive/folders/13GepQrf6B68m1ejxayaXes5e122W6g_K?usp=sharing

package com.hashankur.footballclubs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.hashankur.footballclubs.db.AppDatabase
import com.hashankur.footballclubs.db.ClubDao
import com.hashankur.footballclubs.db.LeagueDao
import com.hashankur.footballclubs.ui.LookupJerseysActivity
import com.hashankur.footballclubs.ui.SearchClubsActivity
import com.hashankur.footballclubs.ui.SearchClubsLeagueActivity
import com.hashankur.footballclubs.ui.addLeagues
import com.hashankur.footballclubs.ui.theme.FootballClubsTheme
import kotlinx.coroutines.launch

lateinit var db: AppDatabase
lateinit var leagueDao: LeagueDao
lateinit var clubDao: ClubDao

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = Room.databaseBuilder(this, AppDatabase::class.java, "football").build()

        setContent {
            FootballClubsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val snackbarHostState = remember { SnackbarHostState() }
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
                        snackbarHost = {
                            SnackbarHost(hostState = snackbarHostState)
                        }
                    ) { innerPadding -> // Padding for top bar
                        val context = LocalContext.current
                        val scope = rememberCoroutineScope()

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxHeight()
                                .verticalScroll(rememberScrollState())

                        ) {
                            val infiniteTransition =
                                rememberInfiniteTransition(label = "infinite transition")
                            val animatedColor by infiniteTransition.animateColor(
                                initialValue = MaterialTheme.colorScheme.primary,
                                targetValue = MaterialTheme.colorScheme.tertiary,
                                animationSpec = infiniteRepeatable(tween(2000), RepeatMode.Reverse),
                                label = "color"
                            )
                            Text(
                                "Select\nOption.",
                                modifier = Modifier
                                    .padding(horizontal = 50.dp)
                                    .padding(bottom = 40.dp)
                                    .fillMaxWidth(),
                                fontSize = 64.sp,
                                lineHeight = 80.sp,
                                fontWeight = FontWeight.Bold,
                                color = animatedColor // https://developer.android.com/jetpack/compose/animation/quick-guide#animate-text-color
                            )
                            MenuButton("Add Leagues to DB", onClick = {
                                scope.launch {
                                    addLeagues(context)
                                    snackbarHostState
                                        .showSnackbar(
                                            message = "Added leagues to database",
                                        )
                                }
                            })
                            MenuButton("Search for Clubs by League", onClick = {
                                navigateToActivity(
                                    context,
                                    SearchClubsLeagueActivity::class.java,
                                )
                            })
                            MenuButton("Search for Clubs", onClick = {
                                navigateToActivity(
                                    context,
                                    SearchClubsActivity::class.java,
                                )
                            })
                            MenuButton("Lookup Jerseys", onClick = {
                                navigateToActivity(
                                    context,
                                    LookupJerseysActivity::class.java,
                                )
                            })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MenuButton(name: String, onClick: () -> Unit) {
    Button(
        onClick,
        Modifier
            .width(400.dp)
            .height(100.dp)
            .padding(20.dp)
    )
    {
        Text(name, fontSize = 20.sp)
    }
}

fun navigateToActivity(context: Context, activity: Class<*>) {
    val i = Intent(context, activity)
    context.startActivity(i)
}

