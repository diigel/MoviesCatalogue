package com.dani.details.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dani.details.data.entity.DetailMovieDto

interface DetailMovieRepository {
    var result : LiveData<DetailMovieDto>
    suspend fun detailMovie(movieId : Int)
}