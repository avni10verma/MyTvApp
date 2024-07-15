package com.example.data.interfaces


import android.content.Context
import androidx.lifecycle.LiveData
import com.example.data.model.MovieResult

interface MovieRepository {
    val movies: LiveData<List<List<MovieResult>>>
    suspend fun fetchAllMovies()
    suspend fun searchMovies(query: String): List<MovieResult>
    fun getAllMoviesFromDb(): List<MovieResult> //method to fetch all movies from SQLite

    fun insertMovie(movie: MovieResult) //  method to insert a movie into SQLite

}

