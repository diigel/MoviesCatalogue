package com.dani.favorites.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dani.favorites.data.entity.FavoriteEntity

@Dao
interface FavoriteDao {

    @Query("select * from favorite order by millis desc")
    suspend fun getMovieFavorite(): List<FavoriteEntity>

    @Insert
    suspend fun insertFavorite(entity: FavoriteEntity)

    @Query("delete from favorite where movie_id = :movieId")
    suspend fun deleteFavorite(movieId: Int)

    @Query("select * from favorite where movie_id = :movieId")
    suspend fun getFavoriteById(movieId: Int): FavoriteEntity
}