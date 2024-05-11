package com.hashankur.footballclubs.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hashankur.footballclubs.data.League

@Dao
interface LeagueDao {
    @Query("SELECT * FROM League")
    suspend fun getAll(): List<League>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg leagues: League)
}