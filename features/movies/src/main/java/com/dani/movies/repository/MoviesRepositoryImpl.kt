package com.dani.movies.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dani.data.Network
import com.dani.movies.dao.MovieDao
import com.dani.movies.data.Services
import com.dani.movies.data.entity.MovieEntity
import com.dani.movies.data.entity.MoviesDto
import com.dani.movies.utils.Mapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(
    private val service: Services,
    private val movieDao: MovieDao
) : MoviesRepository {

    private val _mutableMovies : MutableLiveData<List<MoviesDto>> = MutableLiveData()
    private val _mutableLocalMovie : MutableLiveData<List<MovieEntity>> = MutableLiveData()
    private val _mutableLocalMovieById : MutableLiveData<MovieEntity> = MutableLiveData()

    override var movies: LiveData<List<MoviesDto>> =  _mutableMovies
    override var localMovies: LiveData<List<MovieEntity>> = _mutableLocalMovie
    override var localMoviesById: LiveData<MovieEntity> = _mutableLocalMovieById

    override suspend fun getLocaleMovieList() {
        val localeData = withContext(Dispatchers.IO){
            movieDao.getMovie()
        }
        _mutableLocalMovie.postValue(localeData)
    }

    override suspend fun getLocaleMovieById(movieId: Int) {
        val localeMovieById = withContext(Dispatchers.IO){
            movieDao.getMovieById(movieId)
        }
        _mutableLocalMovieById.postValue(localeMovieById)
    }

    override suspend fun addLocaleMovieById(movieEntity: MovieEntity) {
        withContext(Dispatchers.IO){
            movieDao.insertMovie(movieEntity)
        }
    }

    override suspend fun removeLocaleMovieById(movieEntity: MovieEntity) {
        withContext(Dispatchers.IO){
            movieDao.removeMovie(movieEntity.movieId)
        }
    }

    override suspend fun moviesList() {
        val data = service.getMovies(Network.API_KEY)
        val dataDto = Mapper.mapMoviesResponseToDto(data)
        _mutableMovies.postValue(dataDto)
    }

}