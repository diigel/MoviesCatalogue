package com.dani.details.di

import com.dani.details.data.Services
import com.dani.details.repository.DetailMovieRepository
import com.dani.details.repository.DetailMovieRepositoryImp
import com.dani.details.viewmodel.DetailMovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailMovieServices = module {
    single { Services.create() }
}

val detailMovieRepositoryImp = module {
    single<DetailMovieRepository> { DetailMovieRepositoryImp(get()) }
}

val detailMovieViewModel = module {
    viewModel { DetailMovieViewModel(get()) }
}