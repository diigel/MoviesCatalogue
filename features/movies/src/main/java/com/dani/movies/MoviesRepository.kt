package com.dani.movies

interface MoviesRepository {
    suspend fun moviesList() : List<DiscoverMoviesDto>
}