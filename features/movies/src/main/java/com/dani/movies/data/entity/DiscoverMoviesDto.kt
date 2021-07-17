package com.dani.movies.data.entity

data class DiscoverMoviesDto(
    var id: Int,
    var originalTitle: String,
    var overview: String,
    var posterPath: String,
) {
    companion object {
        fun DiscoverMoviesDto?.nonNullable(): DiscoverMoviesDto {
            return DiscoverMoviesDto(
                id = 0,
                originalTitle = "",
                overview = "",
                posterPath = ""
            )
        }
    }
}