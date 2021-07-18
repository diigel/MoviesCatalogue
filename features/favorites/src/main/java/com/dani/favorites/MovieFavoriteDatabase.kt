package com.dani.favorites

import androidx.room.Database

@Database(entities = [MovieEntity::class],version = 1)
abstract class MovieFavoriteDatabase {
    abstract fun movieFavoriteDao() : FavoriteDao
}