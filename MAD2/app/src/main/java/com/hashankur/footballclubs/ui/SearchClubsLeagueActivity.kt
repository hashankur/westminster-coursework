package com.hashankur.footballclubs.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.ui.unit.dp
import com.hashankur.footballclubs.R
import com.hashankur.footballclubs.clubDao
import com.hashankur.footballclubs.data.Club
import com.hashankur.footballclubs.db
import com.hashankur.footballclubs.fetch
import com.hashankur.footballclubs.ui.components.TopBarBuilder
import com.hashankur.footballclubs.ui.theme.FootballClubsTheme
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

val KEYS = listOf(
    "idTeam",
    "strTeam",
    "strTeamShort",
    "strAlternate",
    "intFormedYear",
    "strLeague",
    "idLeague",
    "strStadium",
    "strKeywords",
    "strStadiumThumb",
    "strStadiumLocation",
    "intStadiumCapacity",
    "strWebsite",
    "strTeamJersey",
    "strTeamLogo"
)

class SearchClubsLeagueActivity : ComponentActivity() {
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
                                getString(R.string.title_activity_search_clubs_league),
                                goBack = { finish() },
                            )
                        },
                        snackbarHost = {
                            SnackbarHost(hostState = snackbarHostState)
                        }
                    ) { innerPadding ->
                        var input by rememberSaveable { mutableStateOf("") }
                        var response by rememberSaveable { mutableStateOf("{\"teams\":[]}") }
                        var savedToDbMessage by rememberSaveable { mutableStateOf(false) }

                        // Creates a CoroutineScope bound to the GUI composable lifecycle
                        val scope = rememberCoroutineScope()

                        LaunchedEffect(savedToDbMessage) {
                            if (savedToDbMessage) {
                                snackbarHostState.showSnackbar(
                                    message = "Saved results to database",
                                    duration = SnackbarDuration.Short
                                )
                                savedToDbMessage = false
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
                                        response =
                                            fetch("https://www.thesportsdb.com/api/v1/json/3/search_all_teams.php?l=$input")
                                    }
                                }) {
                                    Text("Retrieve Clubs")
                                }
                                Spacer(modifier = Modifier.padding(10.dp))
                                Button(onClick = {
                                    scope.launch {
                                        saveToDb(response)
                                        if (!JSONObject(response).getJSONArray("teams")
                                                .isNull(0)
                                        ) savedToDbMessage = true
                                    }
                                }) {
                                    Text("Save Clubs to Database")
                                }
                            }

                            Spacer(modifier = Modifier.padding(10.dp))

                            LazyColumn(Modifier.weight(1f)) {
                                val responseJSON: JSONArray =
                                    JSONObject(response).getJSONArray("teams")
//                                Log.d("res", response.toString())
                                items(responseJSON.length()) {
                                    val sb = StringBuilder()
                                    val current = responseJSON.getJSONObject(it)

                                    // Loop through desired keys and build string
                                    for (key in KEYS) {
                                        if (current.has(key)) {
                                            val value = current.getString(key)
                                            val label = if (key == "strTeam") "Name" else key
                                            sb.appendLine("\"$label\": \"$value\"")
                                        }
                                    }
                                    Text(sb.toString())
                                    Divider(Modifier.padding(vertical = 20.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

suspend fun saveToDb(response: String) {
    val clubList = mutableListOf<Club>()
    val responseJSON: JSONArray =
        JSONObject(response).getJSONArray("teams")

    for (index in 0..<responseJSON.length()) {
        val current = responseJSON.getJSONObject(index)
        clubList.add(
            Club(
                current.getInt(KEYS[0]),
                current.getString(KEYS[1]),
                current.getString(KEYS[2]),
                current.getString(KEYS[3]),
                current.getInt(KEYS[4]),
                current.getString(KEYS[5]),
                current.getInt(KEYS[6]),
                current.getString(KEYS[7]),
                current.getString(KEYS[8]),
                current.getString(KEYS[9]),
                current.getString(KEYS[10]),
                current.getInt(KEYS[11]),
                current.getString(KEYS[12]),
                current.getString(KEYS[13]),
                current.getString(KEYS[14]),
            )
        )
    }

    clubDao.insert(*clubList.toTypedArray())
}