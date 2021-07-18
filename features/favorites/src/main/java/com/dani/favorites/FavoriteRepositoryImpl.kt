package com.dani.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.dani.data.toJson

class FavoriteRepositoryImpl(private val favoriteDao: FavoriteDao) : FavoriteRepository {

    private val _movies: MutableLiveData<List<MovieEntity>> = MutableLiveData()
    private val _movieById : MutableLiveData<MovieEntity> = MutableLiveData()

    override var movies: LiveData<List<MovieEntity>> = _movies
    override var movieById: LiveData<MovieEntity> = _movieById

    override suspend fun getFavoriteMovies() {
        val data = favoriteDao.getMovieFavorite()
        _movies.postValue(data)
    }

    override suspend fun getFavoriteMovie(movie_id: Int) {
        val data = favoriteDao.getFavoriteById(movie_id)
        _movieById.postValue(data)
        println(" data repository is ${data?.toJson()}")

    }

    override suspend fun checkFavorite(movie_id: Int): LiveData<Boolean> {
        val data = favoriteDao.getFavoriteById(movie_id)
        return liveData {
            emit(data != null)
        }
    }

    override suspend fun addFavoriteMovie(movieEntity: MovieEntity) {
        favoriteDao.insertFavorite(movieEntity)
    }

    override suspend fun removeFavoriteMovie(movieEntity: MovieEntity) {
        favoriteDao.deleteFavorite(movieEntity.movieId)
    }
}