package com.dani.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: FavoriteRepository) : ViewModel() {

    val getFavoriteMovie = repository.movies
    val getFavoriteMovieById = repository.movieById

    fun getFavoriteMovies() = viewModelScope.launch {
        repository.getFavoriteMovies()
    }

    fun getFavoriteMovie(movie_id: Int) = viewModelScope.launch {
        repository.getFavoriteMovie(movie_id)
    }

    fun addFavoriteMovie(movieEntity: MovieEntity) = viewModelScope.launch {
        repository.addFavoriteMovie(movieEntity)
    }

    fun removeFavoriteMovie(movieEntity: MovieEntity) = viewModelScope.launch {
        repository.removeFavoriteMovie(movieEntity)
    }

    fun checkFavoriteMovie(movie_id: Int) = viewModelScope.launch {
        repository.checkFavorite(movie_id)
    }
}