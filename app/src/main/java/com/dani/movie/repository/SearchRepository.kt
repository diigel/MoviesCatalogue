package com.dani.movie.repository

import androidx.lifecycle.LiveData
import com.dani.movie.data.entity.SearchDto

interface SearchRepository {
    val search : LiveData<List<SearchDto>>
    suspend fun search(query : String)
}