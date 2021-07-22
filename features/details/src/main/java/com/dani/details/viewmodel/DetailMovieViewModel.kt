package com.dani.details.viewmodel

import androidx.lifecycle.*
import com.dani.details.data.entity.DetailMovieDto
import com.dani.details.repository.DetailMovieRepository
import kotlinx.coroutines.launch

class DetailMovieViewModel(private val repository: DetailMovieRepository) : ViewModel() {

    val detailMovie : LiveData<DetailMovieDto> = repository.result

    private val _requestCheckFavorite: MutableLiveData<Int> = MutableLiveData()
    val requestCheckFavorite: LiveData<Int> = _requestCheckFavorite

    private val _requestInsertFavorite: MutableLiveData<DetailMovieDto> = MutableLiveData()
    private val _requestRemoveFavorite: MutableLiveData<Int> = MutableLiveData()

    val requestInsertFavorite: LiveData<DetailMovieDto> = _requestInsertFavorite
    val requestRemoveFavorite: LiveData<Int> = _requestRemoveFavorite

    private val _hasFavorite: MutableLiveData<Boolean> = MutableLiveData()
    val hasFavorite: LiveData<Boolean> = _hasFavorite

    fun getDetailMovie(movieId : Int) =  viewModelScope.launch {
        repository.detailMovie(movieId)
    }

    fun setHasFavorite(hasFavorite: Boolean) {
        _hasFavorite.postValue(hasFavorite)
    }

    fun checkHasFavorite(movieId: Int?) {
        if (movieId != null) {
            _requestCheckFavorite.postValue(movieId)
        }
    }

    fun insertFavorite(movieDto: DetailMovieDto) {
        _requestInsertFavorite.postValue(movieDto)
    }

    fun removeFavorite(movieId: Int) {
        _requestRemoveFavorite.postValue(movieId)
    }
}