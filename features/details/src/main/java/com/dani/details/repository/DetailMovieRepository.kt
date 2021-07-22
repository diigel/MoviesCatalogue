package com.dani.details.repository

import androidx.lifecycle.MutableLiveData
import com.dani.details.data.entity.DetailMovieDto

interface DetailMovieRepository {
    var result : MutableLiveData<DetailMovieDto>
    suspend fun detailMovie(movieId : Int)
}