package com.dani.movies

import com.dani.data.nonNullable
import com.dani.movies.DiscoverMoviesDto.Companion.nonNullable

object Mapper {

    fun mapDiscoverMoviesResponseToDto(response : DiscoverMoviesResponse)  : List<DiscoverMoviesDto> {
        return response.results?.map {
            DiscoverMoviesDto(
                id = it.id.nonNullable(),
                originalTitle = it.originalTitle.nonNullable(),
                overview = it.overview.nonNullable(),
                posterPath = it.posterPath.nonNullable()
            ).nonNullable()
        } ?: emptyList()
    }
}