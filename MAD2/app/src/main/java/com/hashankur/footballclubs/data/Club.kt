package com.hashankur.footballclubs.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Club(
    @PrimaryKey var idTeam: Int,
    var strTeam: String,
    var strTeamShort: String,
    var strAlternate: String,
    var intFormedYear: Int,
    var strLeague: String,
    var idLeague: Int,
    var strStadium: String,
    var strKeywords: String,
    var strStadiumThumb: String,
    var strStadiumLocation: String,
    var intStadiumCapacity: Int,
    var strWebsite: String,
    var strTeamJersey: String,
    var strTeamLogo: String,
)
