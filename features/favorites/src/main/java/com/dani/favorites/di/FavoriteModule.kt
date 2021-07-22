package com.dani.favorites.di

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import com.dani.favorites.dao.FavoriteDao
import com.dani.favorites.data.FavoriteDatabase
import com.dani.favorites.repository.FavoriteRepository
import com.dani.favorites.repository.FavoriteRepositoryImpl
import com.dani.favorites.viewmodel.FavoriteViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteDatabase =  module {

    fun provideDatabase(application: Application) :FavoriteDatabase{
        return Room.databaseBuilder(
            application,
            FavoriteDatabase::class.java,"favorite.db"
        ).fallbackToDestructiveMigration().build()
    }

    fun provideFavoriteDao(database: FavoriteDatabase) : FavoriteDao {
        return database.movieFavoriteDao()
    }
    single {
        provideDatabase(androidApplication())
    }

    single { provideFavoriteDao(get()) }
}

val favoriteRepository = module {
    single<FavoriteRepository> { FavoriteRepositoryImpl(get()) }
}

val favoriteViewModel = module {

    viewModel { FavoriteViewModel(get()) }

}