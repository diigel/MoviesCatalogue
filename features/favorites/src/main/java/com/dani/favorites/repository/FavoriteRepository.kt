package com.dani.favorites.repository

import androidx.lifecycle.LiveData
import com.dani.favorites.data.entity.MovieEntity

interface FavoriteRepository {
    var movies : LiveData<List<MovieEntity>>
    var movieById : LiveData<MovieEntity>
    suspend fun getFavoriteMovies()
    suspend fun getFavoriteMovie(movie_id: Int)
    suspend fun checkFavorite(movie_id : Int) : LiveData<Boolean>
    suspend fun addFavoriteMovie(movieEntity: MovieEntity)
    suspend fun removeFavoriteMovie(movieEntity: MovieEntity)
}