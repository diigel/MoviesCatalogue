package com.dani.movies.data

import com.dani.data.Network
import com.dani.movies.data.entity.MoviesResponse
import com.dani.movies.data.entity.UpComingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    companion object {
        const val MOVIES = "movie/popular"
        const val UP_COMING = "movie/upcoming"

        fun create(): Services {
            return Network.build().create(Services::class.java)
        }
    }

    @GET(MOVIES)
    suspend fun getMovies(
        @Query("api_key") apiKey : String
    ) : MoviesResponse

    @GET(UP_COMING)
    suspend fun getUpComing(
        @Query("api_key") apiKey : String
    ) : UpComingResponse
}