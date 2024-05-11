package com.hashankur.footballclubs.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hashankur.footballclubs.data.Club
import com.hashankur.footballclubs.data.League

@Database(entities = [League::class, Club::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getLeagueDao(): LeagueDao
    abstract fun getClubDao(): ClubDao
}