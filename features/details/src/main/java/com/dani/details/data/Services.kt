package com.dani.details.data

import com.dani.data.Network
import com.dani.details.data.entity.DetailMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Services {

    companion object {

        const val DETAIL = "movie/{movie_id}"
        fun create() : Services {
            return Network.build().create(Services::class.java)
        }
    }

    @GET(DETAIL)
    suspend fun getDetailMovie(
        @Path("movie_id") movieId : Int,
        @Query("api_key") apiKey : String
    ) : DetailMovieResponse
}