package com.dani.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dani.movies.repository.MoviesRepository
import com.dani.movies.data.entity.DiscoverMoviesDto
import kotlinx.coroutines.launch

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    val movies : LiveData<List<DiscoverMoviesDto>> = moviesRepository.result

    fun getMovies() = viewModelScope.launch {
        println("get movie in viewmodel")
        moviesRepository.moviesList()
    }
}