package com.dani.favorites.repository

import androidx.lifecycle.LiveData
import com.dani.favorites.data.entity.FavoriteEntity

interface FavoriteRepository {
    var movies : LiveData<List<FavoriteEntity>>
    var favoriteById : LiveData<FavoriteEntity>
    suspend fun getFavoriteMovies()
    suspend fun getFavoriteMovie(movieId: Int)
    suspend fun getFavoriteMovieInline(movieId: Int): FavoriteEntity
    suspend fun checkFavorite(movieId : Int) : LiveData<Boolean>
    suspend fun addFavoriteMovie(favoriteEntity: FavoriteEntity)
    suspend fun removeFavoriteMovie(favoriteEntity: FavoriteEntity)
}