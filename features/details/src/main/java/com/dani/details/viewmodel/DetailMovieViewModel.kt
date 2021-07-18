package com.dani.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dani.details.data.entity.DetailMovieDto
import com.dani.details.repository.DetailMovieRepository
import kotlinx.coroutines.launch

class DetailMovieViewModel(private val repository: DetailMovieRepository) : ViewModel() {

    val detailMovie : LiveData<DetailMovieDto> = repository.result

    fun getDetailMovie(movieId : Int) =  viewModelScope.launch {
        println("viewModel detail -----")
        repository.detailMovie(movieId)
    }

}