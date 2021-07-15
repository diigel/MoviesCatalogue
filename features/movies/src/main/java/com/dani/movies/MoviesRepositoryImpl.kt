package com.dani.movies

import com.dani.data.Network

class MoviesRepositoryImpl : MoviesRepository{
    private val service = Services.create()

    override suspend fun moviesList(): List<DiscoverMoviesDto> {
        val data = service.getDiscoverMovies(Network.API_KEY)
        return Mapper.mapDiscoverMoviesResponseToDto(data)
    }

}