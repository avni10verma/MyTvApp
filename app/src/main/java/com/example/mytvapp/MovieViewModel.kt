// MovieViewModel.kt
package com.example.mytvapp


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.data.model.MovieResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: com.example.data.interfaces.MovieRepository) : ViewModel() {


    val movies: LiveData<List<List<MovieResult>>> = repository.movies


    private var searchJob: Job? = null

    fun searchDebounced(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            Log.d("delaycheck", "$query")
            searchMovies(query)
        }
    }

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
        Log.d("MovieViewModel", "Search query: $query")
        viewModelScope.launch {
            result.postValue(repository.searchMovies(query))
        }
        return result
    }
}

