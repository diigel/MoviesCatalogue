package com.dani.movies.data

import com.dani.data.Network
import com.dani.movies.data.entity.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    companion object {
        const val MOVIES = "movie/popular"

        fun create(): Services {
            return Network.build().create(Services::class.java)
        }
    }

    @GET(MOVIES)
    suspend fun getMovies(
        @Query("api_key") apiKey : String
    ) : MoviesResponse
}