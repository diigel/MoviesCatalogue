package com.dani.testutils

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceMock {
    inline fun <reified T> create(baseUrl: HttpUrl,okHttpClient: OkHttpClient? = null) : T = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .apply {
            if (okHttpClient!= null){
                client(okHttpClient)
            }
        }
        .build()
        .create(T::class.java)
}