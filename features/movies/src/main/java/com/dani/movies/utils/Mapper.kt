package com.dani.movies.utils

import com.dani.movies.data.entity.MoviesDto
import com.dani.movies.data.entity.MoviesResponse

object Mapper {

    fun mapMoviesResponseToDto(response: MoviesResponse?): List<MoviesDto> {
        return response?.results?.map { result ->
            MoviesDto(
                id = result?.id ?: 0,
                originalTitle = result?.originalTitle ?: "",
                overview = result?.overview ?: "",
                posterPath = result?.posterPath ?: "",
                releaseDate = result?.releaseDate ?: "",
                backdropPath = result?.backdropPath ?: ""
            )
        } ?: emptyList()
    }
}