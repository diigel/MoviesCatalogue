package com.dani.movies.data.entity

import com.google.gson.annotations.SerializedName

data class UpComingResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val totalPages: Int,
    val totalResults: Int
) {
    data class Dates(
        val maximum: String,
        val minimum: String
    )

    data class Result(
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String,
        val genreIds: List<Int>,
        val id: Int,
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        val overview: String,
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("release_date")
        val releaseDate: String,
        val title: String,
        val video: Boolean,
        val voteAverage: Double,
        val voteCount: Int
    )
}