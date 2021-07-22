package com.dani.movies.viewmodel

import androidx.lifecycle.*
import com.dani.movies.repository.MoviesRepository
import com.dani.movies.data.entity.MoviesDto
import kotlinx.coroutines.launch

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    val movies: LiveData<List<MoviesDto>> = moviesRepository.result

    private val _navigateToDetailMovie : MutableLiveData<Int> = MutableLiveData()
    val navigateToDetailMovie : LiveData<Int> = _navigateToDetailMovie

    fun getMovies() = viewModelScope.launch {
        moviesRepository.moviesList()
    }

    fun navigateToDetailMovie(movieId : Int) {
        _navigateToDetailMovie.postValue(movieId)
    }
}