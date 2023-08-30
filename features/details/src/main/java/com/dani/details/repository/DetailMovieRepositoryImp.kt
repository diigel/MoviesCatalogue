package com.dani.details.repository

import androidx.lifecycle.MutableLiveData
import com.dani.data.*
import com.dani.details.data.Services
import com.dani.details.data.entity.DetailMovieDto
import com.dani.details.utils.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class DetailMovieRepositoryImp(
    private val services: Services
) : DetailMovieRepository {

    override var result: MutableLiveData<DetailMovieDto> = MutableLiveData()

    override suspend fun detailMovie(movieId: Int) {
        val data = services.getDetailMovie(movieId, Network.API_KEY)
        println(data)
        val dataDto = Mapper.mapDetailsMovieResponseToDto(data)
        println(dataDto)
        result.postValue(dataDto)
    }
}