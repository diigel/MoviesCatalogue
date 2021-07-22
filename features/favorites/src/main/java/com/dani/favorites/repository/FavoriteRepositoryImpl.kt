package com.dani.favorites.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.dani.favorites.dao.FavoriteDao
import com.dani.favorites.data.FavoriteDatabase
import com.dani.favorites.data.entity.FavoriteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteRepositoryImpl(private val favoriteDao: FavoriteDao) : FavoriteRepository {

    private val _movies: MutableLiveData<List<FavoriteEntity>> = MutableLiveData()
    private val _favoriteById: MutableLiveData<FavoriteEntity> = MutableLiveData()

    override var movies: LiveData<List<FavoriteEntity>> = _movies
    override var favoriteById: LiveData<FavoriteEntity> = _favoriteById

    override suspend fun getFavoriteMovies() {
        val data = withContext(Dispatchers.IO) {
            favoriteDao.getMovieFavorite()
        }
        _movies.postValue(data)
    }

    override suspend fun getFavoriteMovie(movieId: Int) {
        val data = withContext(Dispatchers.IO) {
            favoriteDao.getFavoriteById(movieId)
        }
        _favoriteById.postValue(data)
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
            favoriteEntity.movieId?.let { favoriteDao.deleteFavorite(it) }
        }
    }
}