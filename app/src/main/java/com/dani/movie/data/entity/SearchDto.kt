package com.dani.movie.data.entity

data class SearchDto(
    var id: Int,
    var originalTitle: String,
    var overview: String,
    var posterPath: String,
    var releaseDate : String,
    var backdropPath : String
)