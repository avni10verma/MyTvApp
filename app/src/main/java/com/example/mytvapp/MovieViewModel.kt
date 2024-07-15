package com.example.mytvapp
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.interfaces.MovieRepository
import com.example.data.model.MovieResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    val movies: LiveData<List<List<MovieResult>>> = repository.movies

    private val _searchResults = MutableLiveData<List<MovieResult>>()
    val searchResults: LiveData<List<MovieResult>> get() = _searchResults

    private var searchJob: Job? = null

    init {
        fetchAllMovies()
    }

    fun fetchAllMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchAllMovies()
        }
    }

    fun searchDebounced(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            Log.d("MovieViewModel", "Delayed search query: $query")
            searchMovies(query)
        }
    }

    private suspend fun searchMovies(query: String) {
        Log.d("Search", "Immediate search query: $query")
        _searchResults.postValue(repository.searchMovies(query))
    }

    fun getAllMoviesFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val dbMovies = repository.getAllMoviesFromDb()
            // Handle dbMovies as needed
        }
    }



}
