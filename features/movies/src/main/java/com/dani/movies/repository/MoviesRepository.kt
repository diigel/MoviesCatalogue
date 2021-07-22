package com.dani.movies.repository

import androidx.lifecycle.MutableLiveData
import com.dani.movies.data.entity.MoviesDto

interface MoviesRepository {
    var result: MutableLiveData<List<MoviesDto>>
    suspend fun moviesList()
}