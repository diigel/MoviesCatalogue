package com.dani.movies.data.entity

data class MoviesDto(
    var id: Int,
    var originalTitle: String,
    var overview: String,
    var voteAverage: Double,
    var posterPath: String,
    var releaseDate : String,
    var backdropPath : String
)