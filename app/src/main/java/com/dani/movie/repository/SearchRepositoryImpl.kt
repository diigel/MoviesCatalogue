package com.dani.movie.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dani.data.Network
import com.dani.data.toJson
import com.dani.movie.data.Services
import com.dani.movie.data.entity.SearchDto
import com.dani.movie.utils.Mapper

class SearchRepositoryImpl(private val services: Services)  : SearchRepository{

    private val _mutableSearch : MutableLiveData<List<SearchDto>> = MutableLiveData()
    override val search: LiveData<List<SearchDto>> = _mutableSearch

    override suspend fun search(query: String) {
        val data = services.search(Network.API_KEY,query)
        val dataDto = Mapper.mapSearchResponseToDto(data.results)
        _mutableSearch.postValue(dataDto)
    }
}