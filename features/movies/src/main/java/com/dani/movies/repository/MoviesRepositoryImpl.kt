package com.dani.movies.repository

import androidx.lifecycle.MutableLiveData
import com.dani.data.Network
import com.dani.movies.data.Services
import com.dani.movies.data.entity.MoviesDto
import com.dani.movies.utils.Mapper

class MoviesRepositoryImpl(
    private val service: Services
) : MoviesRepository {

    override var result: MutableLiveData<List<MoviesDto>> =  MutableLiveData()

    override suspend fun moviesList() {
        println("get movie in repository...")
        val data = service.getMovies(Network.API_KEY)
        println("data response --")
        println(data)
        println("data respose end --")
        val dataDto = Mapper.mapMoviesResponseToDto(data)
        println("this is data repo -----")
        println(dataDto)
        println("this is data repo end -----")
        result.postValue(dataDto)
    }

}