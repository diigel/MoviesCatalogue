package com.dani.favorites

import androidx.room.Room
import com.dani.favorites.data.FavoriteDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val favoriteDatabase =  module {
    single {
        Room.databaseBuilder(
            androidContext(),
            FavoriteDatabase::class.java,"favorite.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}