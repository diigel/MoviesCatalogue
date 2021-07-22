package com.dani.movies.di

import com.dani.movies.data.Services
import com.dani.movies.repository.MoviesRepository
import com.dani.movies.repository.MoviesRepositoryImpl
import com.dani.movies.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieServices = module {
    single { Services.create() }
}

val movieRepository = module {
    single {
        MoviesRepositoryImpl(get()) as MoviesRepository
    }
}

val movieViewModel = module {
    viewModel { MoviesViewModel(get()) }
}