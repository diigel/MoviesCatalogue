package com.dani.movies.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dani.movies.data.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("select * from movie order by milis desc")
    fun getMovie(): List<MovieEntity>

    @Insert
    fun insertMovie(movieEntity: MovieEntity)

    @Query("delete from movie where movie_id = :movieId")
    fun removeMovie(movieId: Int)

    @Query("select * from movie where movie_id = :movieId")
    fun getMovieById(movieId: Int): MovieEntity
}