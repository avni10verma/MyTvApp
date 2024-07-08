package com.example.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.interfaces.MovieApiService
import com.example.data.interfaces.MovieRepository
import com.example.data.model.MovieResult
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class MovieRepositoryImpl(private val tmdbService: MovieApiService) : MovieRepository {

    private val _movies = MutableLiveData<List<List<MovieResult>>>()
    override val movies: LiveData<List<List<MovieResult>>> get() = _movies

    override suspend fun fetchAllMovies() {
        val movieResults = coroutineScope {
            movieFetchers.map { fetcher ->
                async { fetcher() }
            }.map { it.await() }
        }
        _movies.postValue(movieResults)
    }


    override suspend fun searchMovies(query: String): List<MovieResult> {
        val searchResult = tmdbService.searchMovies(query).results
        Log.d("serachcheck", "{$searchResult}")
        return  searchResult
    }

    private suspend fun fetchTrendingMovies(): List<MovieResult> {
        return tmdbService.getTrendingMovies().results
    }

    private suspend fun fetchTopRatedMovies(): List<MovieResult> {
        return tmdbService.getTopRatedMovies().results
    }

    private suspend fun fetchPopularMovies(): List<MovieResult> {
        return tmdbService.getPopularMovies().results
    }

    private suspend fun fetchNowPlayingMovies(): List<MovieResult> {
        return tmdbService.getNowPlayingMovies().results
    }

    private suspend fun fetchUpcomingMovies(): List<MovieResult> {
        return tmdbService.getUpcomingMovies().results
    }

    private val movieFetchers = listOf(
        ::fetchTrendingMovies,
        ::fetchTopRatedMovies,
        ::fetchPopularMovies,
        ::fetchNowPlayingMovies,
        ::fetchUpcomingMovies
    )
}
// {
// [[], [], []]
//
// }


