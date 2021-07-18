package com.dani.details.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dani.data.Network
import com.dani.details.utils.Mapper
import com.dani.details.data.Services
import com.dani.details.data.entity.DetailMovieDto

class DetailMovieRepositoryImp(
    private val services: Services
) : DetailMovieRepository {

    private val _mutableResult: MutableLiveData<DetailMovieDto> = MutableLiveData()
    override var result: LiveData<DetailMovieDto> = _mutableResult

    override suspend fun detailMovie(movieId: Int) {
        println("repo is hereee --- ")
        val data = services.getDetailMovie(movieId, Network.API_KEY)
        println("data response -----")
        println(data)
        println("end data response -----")
        val dataDto = Mapper.mapDetailsMovieResponseToDto(data)
        println("data dto -----")
        println(dataDto)
        println("end data dto -----")
        _mutableResult.postValue(dataDto)
    }
}