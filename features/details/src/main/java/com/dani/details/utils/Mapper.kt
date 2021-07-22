package com.dani.details.utils

import com.dani.details.data.entity.DetailMovieDto
import com.dani.details.data.entity.DetailMovieResponse

object Mapper {

    fun mapDetailsMovieResponseToDto(response: DetailMovieResponse?) : DetailMovieDto {
        return DetailMovieDto(
            id = response?.id ?: 0,
            originalTitle = response?.originalTitle ?: "",
            overview = response?.overview ?: "",
            popularity = response?.popularity ?: 0.0,
            status = response?.status ?: "",
            releaseDate = response?.releaseDate ?: "",
            posterPath = response?.posterPath ?: "",
            backdropPath = response?.backdropPath ?: ""
        )
    }
}