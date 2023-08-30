package com.dani.movies.viewmodel

import androidx.lifecycle.*
import com.dani.movies.data.entity.MovieEntity
import com.dani.movies.repository.MoviesRepository
import com.dani.movies.data.entity.MoviesDto
import com.dani.movies.data.entity.UpComingDto
import kotlinx.coroutines.launch

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    val movies: LiveData<List<MoviesDto>> = moviesRepository.movies
    val upComing: LiveData<List<UpComingDto>> = moviesRepository.upComing
    val localeMovies: LiveData<List<MovieEntity>> = moviesRepository.localMovies

    private val _navigateToDetailMovie : MutableLiveData<Int> = MutableLiveData()
    val navigateToDetailMovie : LiveData<Int> = _navigateToDetailMovie

    fun getMovies() = viewModelScope.launch {
        moviesRepository.moviesList()
    }

    fun getUpComing() = viewModelScope.launch {
        moviesRepository.upComingList()
    }

    fun navigateToDetailMovie(movieId : Int) {
        _navigateToDetailMovie.postValue(movieId)
    }

    fun getLocaleMovies() = viewModelScope.launch {
        moviesRepository.getLocaleMovieList()
    }

    fun addLocaleMovies(moviesEntity: MovieEntity) = viewModelScope.launch {
        moviesRepository.addLocaleMovieById(moviesEntity)
    }

    fun removeLocaleMovies(moviesEntity: MovieEntity) = viewModelScope.launch {
        moviesRepository.removeLocaleMovieById(moviesEntity)
    }
}