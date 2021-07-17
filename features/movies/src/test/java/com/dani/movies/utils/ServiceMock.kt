package com.dani.movies.utils

import com.dani.movies.data.Services
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceMock {
    fun create(baseUrl : HttpUrl) : Services = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Services::class.java)
}