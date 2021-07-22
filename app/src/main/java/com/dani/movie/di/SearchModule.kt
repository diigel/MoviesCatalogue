package com.dani.movie.di

import com.dani.movie.data.Services
import com.dani.movie.repository.SearchRepository
import com.dani.movie.repository.SearchRepositoryImpl
import com.dani.movie.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchServices = module {
    single { Services.create() }
}

val searchRepository = module {
    single<SearchRepository>{ SearchRepositoryImpl(get())}
}

val searchViewModel = module {
    viewModel { SearchViewModel(get()) }
}