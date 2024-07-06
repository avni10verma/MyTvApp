// MovieViewModel.kt
package com.example.mytvapp


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.data.model.MovieResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: com.example.data.interfaces.MovieRepository) : ViewModel() {


    val movies: LiveData<List<List<MovieResult>>> = repository.movies

    init {
        fetchAllMovies()
    }

     fun fetchAllMovies() {
        viewModelScope.launch {
            repository.fetchAllMovies()
        }
    }

    fun searchMovies(query: String): LiveData<List<MovieResult>> {
        val result = MutableLiveData<List<MovieResult>>()
        viewModelScope.launch {
            result.postValue(repository.searchMovies(query))
        }
        return result
    }
}

