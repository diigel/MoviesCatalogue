package com.dani.data

import retrofit2.Retrofit

object Network {
    // Retrofit retrofit = new Retrofit.Builder()
    //    .baseUrl("https://api.github.com/")
    //    .build();

    private const val BASE_URL = "https://api.themoviedb.org"
    const val API_KEY = "50907bc16bd8b78096af6e624c63332d"

    fun  build() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()
    }

}