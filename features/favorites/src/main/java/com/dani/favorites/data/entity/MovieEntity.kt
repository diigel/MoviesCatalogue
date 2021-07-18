package com.dani.favorites.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity(tableName = "favorite")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = Random.nextInt(),
    @ColumnInfo(name = "movie_id")
    val movieId : Int ,
    @ColumnInfo(name = "original_title")
    var originalTitle: String,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "poster_path")
    var posterPath: String,
    @ColumnInfo(name = "millis")
    var millis : Long = System.currentTimeMillis()
)
