package com.dani.favorites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Query("select * from favorite order by millis desc")
    suspend fun getMovieFavorite() : List<MovieEntity>

    @Insert
    suspend fun insertFavorite(entity: MovieEntity)

    @Query("delete from favorite where id = :id")
    suspend fun deleteFavorite(id :Int)

    @Query("select * from favorite where id = :id")
    suspend fun getFavoriteById(id:Int) : MovieEntity
}