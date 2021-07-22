package com.dani.details.data.entity

data class DetailMovieDto(
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val status: String,
    val releaseDate: String,
    val posterPath : String,
    val backdropPath : String
)