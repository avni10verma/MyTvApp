package com.example.data.interfaces


import androidx.lifecycle.LiveData
import com.example.data.model.MovieResult

interface MovieRepository {
    val movies: LiveData<List<List<MovieResult>>>
    suspend fun fetchAllMovies()
    suspend fun searchMovies(query: String): List<MovieResult>
}
