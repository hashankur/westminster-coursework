package com.hashankur.footballclubs.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hashankur.footballclubs.data.Club

@Dao
interface ClubDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg clubs: Club)

    @Query("SELECT * FROM Club WHERE strTeam LIKE '%' || :keywords || '%' OR strLeague LIKE '%' || :keywords || '%'")
    suspend fun search(keywords: String): List<Club>

    @Query("SELECT COUNT(*) = 0 FROM Club")
    suspend fun isEmpty(): Boolean
}