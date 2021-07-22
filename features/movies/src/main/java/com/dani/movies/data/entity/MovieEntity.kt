package com.dani.movies.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = Random.nextInt(),
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "original_title")
    val originalTitle: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "popularity")
    val popularity: Double,
    @ColumnInfo(name = "milis")
    val millis: Long = System.currentTimeMillis()
)
