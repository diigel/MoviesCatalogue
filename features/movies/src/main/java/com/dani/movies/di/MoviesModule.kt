package com.dani.movies.di

import android.app.Application
import androidx.room.Room
import com.dani.movies.dao.MovieDao
import com.dani.movies.data.MovieDatabase
import com.dani.movies.data.Services
import com.dani.movies.repository.MoviesRepository
import com.dani.movies.repository.MoviesRepositoryImpl
import com.dani.movies.viewmodel.MoviesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieServices = module {
    single { Services.create() }
}

val movieDatabase = module {
    fun provideDatabase(application: Application) :MovieDatabase{
        return Room.databaseBuilder(
            application,
            MovieDatabase::class.java,"movie.db"
        ).fallbackToDestructiveMigration().build()
    }

    fun provideFavoriteDao(database: MovieDatabase) : MovieDao {
        return database.movieDao()
    }
    single {
        provideDatabase(androidApplication())
    }

    single { provideFavoriteDao(get()) }
}

val movieRepository = module {
    single<MoviesRepository> {
        MoviesRepositoryImpl(get(),get())
    }
}

val movieViewModel = module {
    viewModel { MoviesViewModel(get()) }
}