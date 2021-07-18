package com.dani.data

import com.google.gson.GsonBuilder


fun Any.toJson(): String {
    val gson = GsonBuilder()
        .setPrettyPrinting()
        .create()
    return gson.toJson(this)
}
