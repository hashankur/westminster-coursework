// Link to video: https://drive.google.com/drive/folders/1vIh1DfINwRUDvRdqHr7H1MG5KoTNwGG1?usp=sharing

package com.hashankur.countryflags

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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hashankur.countryflags.ui.GuessAdvancedActivity
import com.hashankur.countryflags.ui.GuessCountryActivity
import com.hashankur.countryflags.ui.GuessFlagActivity
import com.hashankur.countryflags.ui.GuessHintsActivity
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
                    var timer by rememberSaveable { mutableStateOf(false) }

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
                                "Choose\nYour\nMode.",
                                modifier = Modifier
                                    .padding(horizontal = 50.dp)
                                    .padding(bottom = 40.dp)
                                    .fillMaxWidth(),
                                fontSize = 64.sp,
                                lineHeight = 80.sp,
                                fontWeight = FontWeight.Bold,
                                color = animatedColor // https://developer.android.com/jetpack/compose/animation/quick-guide#animate-text-color
                            )
                            MenuButton("Guess the Country", onClick = {
                                navigateToActivity(
                                    context,
                                    GuessCountryActivity::class.java,
                                    timer
                                )
                            })
                            MenuButton("Guess Hints", onClick = {
                                navigateToActivity(
                                    context,
                                    GuessHintsActivity::class.java,
                                    timer
                                )
                            })
                            MenuButton("Guess the Flag", onClick = {
                                navigateToActivity(
                                    context,
                                    GuessFlagActivity::class.java,
                                    timer
                                )
                            })
                            MenuButton("Advanced Level", onClick = {
                                navigateToActivity(
                                    context,
                                    GuessAdvancedActivity::class.java,
                                    timer
                                )
                            })

                            Spacer(Modifier.padding(vertical = 10.dp))

                            Row(
                                Modifier
                                    .height(20.dp)
                            ) {
                                Text("Timer", Modifier.padding(end = 10.dp))
                                Switch(
                                    checked = timer,
                                    onCheckedChange = {
                                        timer = it
                                    }
                                )
                            }
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

fun navigateToActivity(context: Context, activity: Class<*>, timer: Boolean) {
    val i = Intent(context, activity)
    i.putExtra("timer", timer)
    context.startActivity(i)
}

