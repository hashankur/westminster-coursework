package com.hashankur.footballclubs.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.hashankur.footballclubs.R
import com.hashankur.footballclubs.db
import com.hashankur.footballclubs.fetch
import com.hashankur.footballclubs.getImage
import com.hashankur.footballclubs.leagueDao
import com.hashankur.footballclubs.ui.components.TopBarBuilder
import com.hashankur.footballclubs.ui.theme.FootballClubsTheme
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class LookupJerseysActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        leagueDao = db.getLeagueDao()

        setContent {
            FootballClubsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            TopBarBuilder(
                                getString(R.string.title_activity_lookup_jerseys),
                                goBack = { finish() },
                            )
                        },
                    ) { innerPadding ->
                        var input by rememberSaveable { mutableStateOf("") }
                        val resTeams = rememberSaveable { mutableMapOf<Int, String>() }
                        var resEquipment by rememberSaveable { mutableStateOf(listOf(JSONArray())) }
                        var test by rememberSaveable {
                            mutableStateOf(listOf<Int>())
                        }

                        // Creates a CoroutineScope bound to the GUI composable lifecycle
                        val scope = rememberCoroutineScope()

                        LaunchedEffect(resTeams) {
                            leagueDao.getAll().forEach { league ->
                                val teams =
                                    JSONObject(fetch("https://www.thesportsdb.com/api/v1/json/3/search_all_teams.php?l=" + league.league)).getJSONArray(
                                        "teams"
                                    )
                                for (index in 0..<teams.length()) {
                                    val current = teams[index] as JSONObject
                                    resTeams[current.getInt("idTeam")] =
                                        current.getString("strTeam")
                                }
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
                                label = { Text("Search for clubs") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                            Row {
                                Button(onClick = {
//                                    val teamEquipment = mutableListOf<JSONArray>()
                                    val temp = mutableListOf<Int>()
                                    scope.launch {
                                        resTeams.forEach { (teamId, teamName) ->
                                            if (teamName.lowercase().contains(input.lowercase())) {
                                                Log.d("t", teamName)
                                                temp.add(teamId)
//                                                teamEquipment.add(
//                                                    JSONObject(
//                                                        fetch("https://www.thesportsdb.com/api/v1/json/3/lookupequipment.php?id=$teamId")
//                                                    )
//                                                        .getJSONArray("equipment")
//                                                )
                                            }
                                        }
//                                        resEquipment = teamEquipment.toList()
                                        test = temp
                                    }
                                }) {
                                    Text("Retrieve Jerseys")
                                }
                            }

                            Spacer(modifier = Modifier.padding(10.dp))

                            LaunchedEffect(resEquipment) {
                                val temp = mutableListOf<JSONArray>()
                                test.forEach { teamId ->
                                    val equipment = JSONObject(
                                        fetch("https://www.thesportsdb.com/api/v1/json/3/lookupequipment.php?id=$teamId")
                                    ).getJSONArray("equipment")
                                    temp.add(equipment)
                                    Log.d("44", equipment.toString())
                                }
                                resEquipment = temp
                            }

                            LazyColumn(Modifier.weight(1f)) {
                                items(test.size) { index ->
                                    resTeams[test[index]]?.let { Text(it) }
//                                    }

                                    LazyRow(
                                        Modifier.weight(1f),
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        if (resEquipment.isNotEmpty()) {
                                            val currentClub: JSONArray = resEquipment[index]
                                            Log.d("1", currentClub.toString())
                                            items(currentClub.length()) {
                                                val currentJersey = currentClub.getJSONObject(it)
                                                val imgUrl =
                                                    URL(currentJersey.getString("strEquipment") + "/preview")
                                                var image by remember {
                                                    mutableStateOf<ImageBitmap?>(
                                                        null
                                                    )
                                                }
                                                LaunchedEffect(imgUrl) {
                                                    image = getImage(imgUrl)
                                                }

                                                Card(
                                                    Modifier.size(
                                                        width = 150.dp,
                                                        height = 180.dp
                                                    )
                                                ) {
                                                    if (image != null) {
                                                        Image(
                                                            bitmap = image!!,
                                                            contentDescription = currentJersey.getString(
                                                                "strSeason"
                                                            ),
                                                            contentScale = ContentScale.Fit,
                                                            modifier = Modifier
                                                                .size(150.dp)
                                                                .padding(10.dp)
                                                        )
                                                    }
                                                    Text(
                                                        currentJersey.getString("strSeason"),
                                                        Modifier
                                                            .align(Alignment.CenterHorizontally)
                                                            .padding(bottom = 10.dp)
                                                    )
                                                }
                                            }
                                        }
                                    }
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