package com.dani.movies

import com.dani.data.Network
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    companion object {
        private const val DISCOVER = "/discover/movie"

        fun create(): Services {
            return Network.build().create(Services::class.java)
        }
    }

    @GET(DISCOVER)
    suspend fun getDiscoverMovies(
        @Query("apikey") apiKey : String,
        @Query("sort_by") sortBy : String = "popularity.asc"
    ) : DiscoverMoviesResponse
}