package com.dani.movie

import android.app.Application
import com.dani.details.di.detailMovieRepositoryImp
import com.dani.details.di.detailMovieServices
import com.dani.details.di.detailMovieViewModel
import com.dani.favorites.favoriteDatabase
import com.dani.movies.di.movieRepositoryImpl
import com.dani.movies.di.movieServices
import com.dani.movies.di.movieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                movieServices,
                movieRepositoryImpl,
                movieViewModel,
                detailMovieServices,
                detailMovieRepositoryImp,
                detailMovieViewModel,
                favoriteDatabase
            )
        }
    }
}