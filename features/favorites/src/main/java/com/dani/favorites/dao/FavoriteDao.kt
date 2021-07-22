package com.dani.favorites.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dani.favorites.data.entity.FavoriteEntity

@Dao
interface FavoriteDao {

    @Query("select * from favorite order by millis desc")
     fun getMovieFavorite() : List<FavoriteEntity>

    @Insert
     fun insertFavorite(entity: FavoriteEntity)

    @Query("delete from favorite where movie_id = :movie_id")
    fun deleteFavorite(movie_id :Int)

    @Query("select * from favorite where movie_id = :movie_id")
    fun getFavoriteById(movie_id:Int) : FavoriteEntity
}