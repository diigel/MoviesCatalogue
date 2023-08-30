package com.dani.details.utils

import com.dani.details.data.entity.DetailMovieDto
import com.dani.details.data.entity.DetailMovieResponse

object Mapper {

    fun mapDetailsMovieResponseToDto(response: DetailMovieResponse?) : DetailMovieDto {
        return DetailMovieDto(
            id = response?.id ?: 0,
            originalTitle = response?.originalTitle.orEmpty(),
            overview = response?.overview.orEmpty(),
            popularity = response?.popularity ?: 0.0,
            status = response?.status.orEmpty(),
            releaseDate = response?.releaseDate.orEmpty(),
            posterPath = response?.posterPath.orEmpty(),
            backdropPath = response?.backdropPath.orEmpty(),
            voteAverage = response?.voteAverage ?: 0.0
        )
    }
}