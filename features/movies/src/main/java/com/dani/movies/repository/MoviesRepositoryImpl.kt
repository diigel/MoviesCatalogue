package com.dani.movies.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dani.data.Network
import com.dani.movies.data.Services
import com.dani.movies.data.entity.DiscoverMoviesDto
import com.dani.movies.utils.Mapper

class MoviesRepositoryImpl(
    private val service: Services
) : MoviesRepository {

    private val _mutableResult: MutableLiveData<List<DiscoverMoviesDto>> = MutableLiveData()
    override var result: LiveData<List<DiscoverMoviesDto>> = _mutableResult

    override suspend fun moviesList() {
        println("get movie in repository...")
        val data = service.getDiscoverMovies(Network.API_KEY)
        println("data response --")
        println(data)
        println("data respose end --")
        val dataDto = Mapper.mapDiscoverMoviesResponseToDto(data)
        println("this is data repo -----")
        println(dataDto)
        println("this is data repo end -----")
        _mutableResult.postValue(dataDto)
    }

}