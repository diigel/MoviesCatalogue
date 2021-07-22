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

    override suspend fun deleteFavorite(movieId: Int) {
        val findMovie =  favoriteList.find { it.movieId == movieId }
        favoriteList.remove(findMovie)
    }

    override suspend fun getFavoriteById(movieId: Int): FavoriteEntity {
        val favoriteList = favoriteList.find { it.movieId == movieId }
        return favoriteList!!
    }
}