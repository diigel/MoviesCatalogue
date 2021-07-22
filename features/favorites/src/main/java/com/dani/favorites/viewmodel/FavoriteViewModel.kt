package com.dani.favorites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dani.favorites.data.entity.FavoriteEntity
import com.dani.favorites.repository.FavoriteRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: FavoriteRepository) : ViewModel() {

    val getFavoriteMovie = repository.movies
    val getFavoriteMovieById = repository.favoriteById

    private val _navigateToDetailMovie : MutableLiveData<Int> = MutableLiveData()
    val navigateToDetailMovie : LiveData<Int> = _navigateToDetailMovie

    fun getFavoriteMovies() = viewModelScope.launch {
        repository.getFavoriteMovies()
    }

    fun getFavoriteMovie(movieId: Int) = viewModelScope.launch {
        repository.getFavoriteMovie(movieId)
    }

    fun addFavoriteMovie(favoriteEntity: FavoriteEntity) = viewModelScope.launch {
        repository.addFavoriteMovie(favoriteEntity)
    }

    fun removeFavoriteMovie(movieId: Int) = viewModelScope.launch {
        val movieIds = repository.getFavoriteMovieInline(movieId)
            repository.removeFavoriteMovie(movieIds)
    }

    suspend fun requestCheckFavoriteById(movieId: Int): LiveData<Boolean> {
        return repository.checkFavorite(movieId)
    }

    fun navigateToDetailMovie(movieId : Int) {
        _navigateToDetailMovie.postValue(movieId)
    }
}