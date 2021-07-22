package com.dani.movies

import com.dani.movies.dao.MovieDao
import com.dani.movies.data.entity.MovieEntity

class MoviesFakeDao : MovieDao{

    private val movieList : MutableList<MovieEntity> = mutableListOf()

    override fun getMovie(): List<MovieEntity> {
     return movieList
    }

    override fun insertMovie(movieEntity: MovieEntity) {
        movieList.add(movieEntity)
    }

    override fun removeMovie(movieId: Int) {
        val findMovies = movieList.find { it.movieId == movieId }
        movieList.remove(findMovies)
    }

    override fun getMovieById(movieId: Int): MovieEntity {
        return movieList.find { it.movieId == movieId }!!
    }
}