package com.dani.movies.utils

import com.dani.movies.data.entity.MovieEntity
import com.dani.movies.data.entity.MoviesDto
import com.dani.movies.data.entity.MoviesResponse
import com.dani.movies.data.entity.UpComingDto
import com.dani.movies.data.entity.UpComingResponse

object Mapper {

    fun mapMoviesResponseToDto(response: MoviesResponse?): List<MoviesDto> {
        return response?.results?.map { result ->
            MoviesDto(
                id = result?.id ?: 0,
                originalTitle = result?.originalTitle ?: "",
                overview = result?.overview ?: "",
                posterPath = result?.posterPath ?: "",
                releaseDate = result?.releaseDate ?: "",
                backdropPath = result?.backdropPath ?: "",
                voteAverage = result?.voteAverage ?: 0.0
            )
        } ?: emptyList()
    }

    fun mapMovieEntityToDto(listMovieEntity: List<MovieEntity>): List<MoviesDto> {
        return listMovieEntity.map { movieEntity ->
            MoviesDto(
                id = movieEntity.movieId,
                originalTitle = movieEntity.originalTitle,
                overview = movieEntity.overview,
                posterPath = movieEntity.posterPath,
                releaseDate = movieEntity.releaseDate,
                backdropPath = movieEntity.backdropPath,
                voteAverage = movieEntity.voteAverage
            )
        }
    }

    fun mapUpComingResponseToDto(response: UpComingResponse?): List<UpComingDto> {
        return response?.results?.map { result ->
            UpComingDto(
                id = result.id,
                originalTitle = result.originalTitle,
                releaseDate = result.releaseDate,
                backdropPath = result.backdropPath,
                posterPath = result.posterPath,
            )
        } ?: emptyList()
    }
}