package com.dani.favorites.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dani.favorites.data.entity.MovieEntity

@Dao
interface FavoriteDao {

    @Query("select * from favorite order by millis desc")
    suspend fun getMovieFavorite() : List<MovieEntity>

    @Insert
    suspend fun insertFavorite(entity: MovieEntity)

    @Query("delete from favorite where movie_id = :movie_id")
    suspend fun deleteFavorite(movie_id :Int)

    @Query("select * from favorite where movie_id = :movie_id")
    suspend fun getFavoriteById(movie_id:Int) : MovieEntity?
}