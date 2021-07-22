package com.dani.movie.utils

import com.dani.details.data.entity.DetailMovieDto
import com.dani.favorites.data.entity.FavoriteEntity
import com.dani.movie.data.entity.SearchDto
import com.dani.movie.data.entity.SearchResponse

object Mapper {

    fun mapDetailMovieDtoToFavoriteEntity(movieDto: DetailMovieDto): FavoriteEntity {
        return FavoriteEntity(
            movieId = movieDto.id,
            originalTitle = movieDto.originalTitle,
            overview = movieDto.overview,
            posterPath = movieDto.posterPath,
            backdropPath = movieDto.backdropPath,
            releaseDate = movieDto.releaseDate,
            popularity = movieDto.popularity,
            millis = System.currentTimeMillis()
        )
    }

    fun mapSearchResponseToDto(searchResponse: List<SearchResponse.Result?>?): List<SearchDto> {
        return searchResponse?.map { search ->
            SearchDto(
                id = search?.id ?: 0,
                originalTitle = search?.originalTitle ?: "",
                overview = search?.overview ?: "",
                posterPath = search?.posterPath ?: "",
                releaseDate = search?.releaseDate ?: "",
                backdropPath = search?.backdropPath ?: ""
            )
        } ?: emptyList()
    }
}