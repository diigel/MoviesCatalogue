package com.dani.movies.utils

import com.dani.data.nonNullable
import com.dani.movies.data.entity.DiscoverMoviesDto
import com.dani.movies.data.entity.DiscoverMoviesResponse

object Mapper {

    fun mapDiscoverMoviesResponseToDto(response : DiscoverMoviesResponse)  : List<DiscoverMoviesDto> {
        return response.results?.map {
            DiscoverMoviesDto(
                id = it.id.nonNullable(),
                originalTitle = it.originalTitle.nonNullable(),
                overview = it.overview.nonNullable(),
                posterPath = it.posterPath.nonNullable()
            )
        } ?: emptyList()
    }
}