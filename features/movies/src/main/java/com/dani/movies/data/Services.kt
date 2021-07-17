package com.dani.movies.data

import com.dani.data.Network
import com.dani.movies.data.entity.DiscoverMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    companion object {
        const val DISCOVER = "discover/movie"

        fun create(): Services {
            return Network.build().create(Services::class.java)
        }
    }

    @GET(DISCOVER)
    suspend fun getDiscoverMovies(
        @Query("api_key") apiKey : String,
        @Query("sort_by") sortBy : String = "popularity.asc"
    ) : DiscoverMoviesResponse
}