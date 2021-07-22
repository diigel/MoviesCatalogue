package com.dani.movie.data

import com.dani.data.Network
import com.dani.movie.data.entity.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    companion object {
        const val SEARCH = "search/movie"

        fun create() : Services {
            return Network.build().create(Services::class.java)
        }
    }


    @GET(SEARCH)
    suspend fun search(
        @Query("api_key") apiKey : String,
        @Query("query") query : String
    ) : SearchResponse
}