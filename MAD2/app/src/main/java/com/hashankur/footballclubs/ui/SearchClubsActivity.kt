package com.hashankur.footballclubs.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hashankur.footballclubs.R
import com.hashankur.footballclubs.clubDao
import com.hashankur.footballclubs.data.Club
import com.hashankur.footballclubs.db
import com.hashankur.footballclubs.getImage
import com.hashankur.footballclubs.ui.components.TopBarBuilder
import com.hashankur.footballclubs.ui.theme.FootballClubsTheme
import kotlinx.coroutines.launch
import java.net.URL

class SearchClubsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clubDao = db.getClubDao()

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
                            TopBarBuilder(
                                getString(R.string.title_activity_search_clubs),
                                goBack = { finish() },
                            )
                        },
                        snackbarHost = {
                            SnackbarHost(hostState = snackbarHostState)
                        }
                    ) { innerPadding ->
                        var input by rememberSaveable { mutableStateOf("") }
                        var searchResults by rememberSaveable { mutableStateOf(listOf<Club>()) }

                        // Creates a CoroutineScope bound to the GUI composable lifecycle
                        val scope = rememberCoroutineScope()

                        LaunchedEffect(snackbarHostState) {
                            if (clubDao.isEmpty()) {
                                snackbarHostState
                                    .showSnackbar(
                                        message = "No clubs in database",
                                        duration = SnackbarDuration.Indefinite
                                    )
                            }
                        }

                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(innerPadding)
                                .padding(16.dp)
                                .fillMaxHeight()
                                .verticalScroll(rememberScrollState())
                        ) {
                            OutlinedTextField(
                                value = input,
                                onValueChange = {
                                    input = it
                                },
                                label = { Text("Search") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                            Row {
                                Button(onClick = {
                                    scope.launch {
                                        searchResults = clubDao.search(input)
                                    }
                                }) {
                                    Text("Search")
                                }
                            }

                            Spacer(modifier = Modifier.padding(10.dp))

                            LazyColumn(Modifier.weight(1f)) {
                                items(searchResults.size) {
                                    val current = searchResults[it]
                                    val imgUrl = URL(current.strTeamLogo + "/preview")
                                    var image by remember { mutableStateOf<ImageBitmap?>(null) }
//                                    var image: ImageBitmap = ImageBitmap(50, 50)
                                    LaunchedEffect(imgUrl) {
                                        image = getImage(imgUrl)
                                    }
                                    ListItem(
                                        headlineContent = { Text(current.strTeam) },
                                        trailingContent = {
                                            if (image != null) {
                                                Image(
                                                    bitmap = image!!,
                                                    contentDescription = current.strTeam,
                                                    contentScale = ContentScale.Fit,
                                                    modifier = Modifier.size(
                                                        width = 150.dp,
                                                        height = Dp.Unspecified
                                                    )
                                                )
                                            }
                                        },
                                        supportingContent = { Text(current.strLeague) }
                                    )
                                    Divider()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}