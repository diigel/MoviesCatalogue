package com.dani.movies.utils

import com.dani.movies.data.entity.DiscoverMoviesDto
import com.dani.movies.data.entity.DiscoverMoviesResponse

object Mapper {

    fun mapDiscoverMoviesResponseToDto(response : DiscoverMoviesResponse)  : List<DiscoverMoviesDto> {
        return response.results?.map {
            DiscoverMoviesDto(
                id = it.id ?: 0,
                originalTitle = it.originalTitle ?:"",
                overview = it.overview ?: "",
                posterPath = it.posterPath ?: ""
            )
        } ?: emptyList()
    }
}