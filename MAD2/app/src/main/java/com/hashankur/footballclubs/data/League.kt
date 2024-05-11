package com.hashankur.footballclubs.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class League(
    @PrimaryKey var id: Int = 0,
    var league: String,
    var sport: String,
    var leagueAlt: String
)