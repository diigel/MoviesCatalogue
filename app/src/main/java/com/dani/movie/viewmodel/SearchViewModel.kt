package com.dani.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dani.movie.data.entity.SearchDto
import com.dani.movie.repository.SearchRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: SearchRepository) : ViewModel() {

    val requestSearch : LiveData<List<SearchDto>> = repository.search

    private val _navigateToDetailMovie : MutableLiveData<Int> = MutableLiveData()
    val navigateToDetailMovie : LiveData<Int> = _navigateToDetailMovie

    fun requestSearch(query : String) = viewModelScope.launch {
        repository.search(query)
    }

    fun navigateToDetailMovie(movieId : Int) {
        _navigateToDetailMovie.postValue(movieId)
    }
}