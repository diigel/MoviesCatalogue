package com.dani.favorites

import com.dani.favorites.dao.FavoriteDao
import com.dani.favorites.data.entity.FavoriteEntity

class FavoriteFakeDao : FavoriteDao {

    private val favoriteList : MutableList<FavoriteEntity> = mutableListOf()

    override suspend fun getMovieFavorite(): List<FavoriteEntity> {
        return favoriteList
    }

    override suspend fun insertFavorite(entity: FavoriteEntity) {
        favoriteList.add(entity)
    }

    override suspend fun deleteFavorite(movie_id: Int) {
        val findMovie =  favoriteList.find { it.movieId == movie_id }
        favoriteList.remove(findMovie)
    }

    override suspend fun getFavoriteById(movie_id: Int): FavoriteEntity? {
        return favoriteList.find { it.movieId == movie_id }
    }
}