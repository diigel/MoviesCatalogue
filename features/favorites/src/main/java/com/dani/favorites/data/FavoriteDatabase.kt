package com.dani.favorites.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dani.favorites.dao.FavoriteDao
import com.dani.favorites.data.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun movieFavoriteDao() : FavoriteDao
}