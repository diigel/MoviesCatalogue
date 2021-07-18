package com.dani.movies.di

import com.dani.movies.data.Services
import com.dani.movies.repository.MoviesRepositoryImpl
import com.dani.movies.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieServices = module {
    single { Services.create() }
}

val movieRepositoryImpl = module {
    single { MoviesRepositoryImpl(get()) }
}

val movieViewModel = module {
    viewModel { MoviesViewModel(get()) }
}