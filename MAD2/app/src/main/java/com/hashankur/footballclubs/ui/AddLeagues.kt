package com.hashankur.footballclubs.ui

import android.content.Context
import com.hashankur.footballclubs.data.League
import com.hashankur.footballclubs.db
import com.hashankur.footballclubs.leagueDao
import com.hashankur.footballclubs.readJSON
import org.json.JSONArray

suspend fun addLeagues(context: Context) {
    leagueDao = db.getLeagueDao()
    val footballLeagues: JSONArray = readJSON(context).getJSONArray("leagues")

    for (index in 0..<footballLeagues.length()) {
        val current = footballLeagues.getJSONObject(index)
        leagueDao.insert(
            League(
                current.getInt("idLeague"),
                current.getString("strLeague"),
                current.getString("strSport"),
                current.getString("strLeagueAlternate"),
            ),
        )
    }
}