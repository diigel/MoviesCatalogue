package com.dani.details.data.entity

data class DetailMovieDto(
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val status: String,
    val backdrop_path: String,
    val poster_path: String
)