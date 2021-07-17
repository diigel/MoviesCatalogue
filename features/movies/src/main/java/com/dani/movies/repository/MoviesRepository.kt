package com.dani.movies.repository

import androidx.lifecycle.LiveData
import com.dani.movies.data.entity.DiscoverMoviesDto

interface MoviesRepository {
    var result: LiveData<List<DiscoverMoviesDto>>
    suspend fun moviesList()
}