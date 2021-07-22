package com.dani.movies.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dani.movies.dao.MovieDao
import com.dani.movies.data.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}