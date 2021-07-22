package com.dani.favorites.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.dani.data.toJson
import com.dani.favorites.data.FavoriteDatabase
import com.dani.favorites.data.entity.FavoriteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteRepositoryImpl(favoriteDatabase: FavoriteDatabase) : FavoriteRepository {

    private val _movies: MutableLiveData<List<FavoriteEntity>> = MutableLiveData()
    private val _favoriteById : MutableLiveData<FavoriteEntity> = MutableLiveData()
    private val favoriteDao = favoriteDatabase.movieFavoriteDao()

    override var movies: LiveData<List<FavoriteEntity>> = _movies
    override var favoriteById: LiveData<FavoriteEntity> = _favoriteById

    override suspend fun getFavoriteMovies() {
        withContext(Dispatchers.IO) {
            val data = favoriteDao.getMovieFavorite()
            _movies.postValue(data)
        }
    }

    override suspend fun getFavoriteMovie(movieId: Int) {
        val data = withContext(Dispatchers.IO) {
            favoriteDao.getFavoriteById(movieId)
        }
        _favoriteById.postValue(data)
        println(" data repository is ${data.toJson()}")

    }

    override suspend fun getFavoriteMovieInline(movieId: Int): FavoriteEntity {
        return withContext(Dispatchers.IO) {
            favoriteDao.getFavoriteById(movieId)
        }
    }

    override suspend fun checkFavorite(movieId: Int): LiveData<Boolean> {
        val data = withContext(Dispatchers.IO) {
            favoriteDao.getFavoriteById(movieId)
        }
        return liveData {
            emit(data != null)
        }
    }

    override suspend fun addFavoriteMovie(favoriteEntity: FavoriteEntity) {
        withContext(Dispatchers.IO) {
            favoriteDao.insertFavorite(favoriteEntity)
        }
    }

    override suspend fun removeFavoriteMovie(favoriteEntity: FavoriteEntity) {
        withContext(Dispatchers.IO) {
            favoriteDao.deleteFavorite(favoriteEntity.movieId)
        }
    }
}