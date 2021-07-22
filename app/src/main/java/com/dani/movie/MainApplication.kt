package com.dani.movie

import com.dani.favorites.di.favoriteDatabase
import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.dani.details.di.detailMovieRepositoryImp
import com.dani.details.di.detailMovieServices
import com.dani.details.di.detailMovieViewModel
import com.dani.favorites.di.favoriteRepository
import com.dani.favorites.di.favoriteViewModel
import com.dani.movie.di.searchRepository
import com.dani.movie.di.searchServices
import com.dani.movie.di.searchViewModel
import com.dani.movies.di.movieRepository
import com.dani.movies.di.movieServices
import com.dani.movies.di.movieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // disable night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // setup koin
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)

            modules(
                searchServices,
                searchRepository,
                searchViewModel,
                movieServices,
                movieRepository,
                movieViewModel,
                detailMovieServices,
                detailMovieRepositoryImp,
                detailMovieViewModel,
                favoriteDatabase,
                favoriteRepository,
                favoriteViewModel
            )
        }
    }
}