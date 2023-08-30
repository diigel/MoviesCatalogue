package com.dani.movies.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dani.movies.data.entity.MovieEntity
import com.dani.movies.data.entity.MoviesDto
import com.dani.movies.data.entity.UpComingDto

interface MoviesRepository {

    var movies: LiveData<List<MoviesDto>>
    var upComing: LiveData<List<UpComingDto>>
    suspend fun moviesList()
    suspend fun upComingList()

    var localMovies: LiveData<List<MovieEntity>>
    var localMoviesById: LiveData<MovieEntity>
    suspend fun getLocaleMovieList()
    suspend fun getLocaleMovieById(movieId: Int)
    suspend fun addLocaleMovieById(movieEntity: MovieEntity)
    suspend fun removeLocaleMovieById(movieEntity: MovieEntity)
}