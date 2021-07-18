package com.dani.favorites

class FavoriteFakeDao : FavoriteDao {


    private val favoriteList : MutableList<MovieEntity> = mutableListOf()

    override suspend fun getMovieFavorite(): List<MovieEntity> {
        return favoriteList
    }

    override suspend fun insertFavorite(entity: MovieEntity) {
        favoriteList.add(entity)
    }

    override suspend fun deleteFavorite(movie_id: Int) {
        val findMovie =  favoriteList.find { it.movieId == movie_id }
        favoriteList.remove(findMovie)
    }

    override suspend fun getFavoriteById(movie_id: Int): MovieEntity? {
        return favoriteList.find { it.movieId == movie_id }
    }
}