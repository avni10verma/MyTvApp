package com.example.data.repository

import android.content.ContentValues
import android.database.Cursor
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.interfaces.MovieApiService
import com.example.data.interfaces.MovieRepository
import com.example.data.local.MovieDbHelper
import com.example.data.model.MovieDatabaseContract
import com.example.data.model.MovieResult
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import android.content.Context



class MovieRepositoryImpl(private val tmdbService: MovieApiService,context: Context) : MovieRepository {


    private val dbHelper = MovieDbHelper(context)
    private val _movies = MutableLiveData<List<List<MovieResult>>>()
    override val movies: LiveData<List<List<MovieResult>>> get() = _movies

    override suspend fun fetchAllMovies() {
        val movieResults = coroutineScope {
            movieFetchers.map { fetcher ->
                async { fetcher() }
            }.map { it.await() }
        }
        _movies.postValue(movieResults)
        // Save fetched data to SQLite
        movieResults.flatten().forEach { insertMovie(it) }
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

    override fun getAllMoviesFromDb(): List<MovieResult> {
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            MovieDatabaseContract.MovieEntry.COLUMN_NAME_ID,
            MovieDatabaseContract.MovieEntry.COLUMN_NAME_TITLE,
            MovieDatabaseContract.MovieEntry.COLUMN_NAME_OVERVIEW,
            MovieDatabaseContract.MovieEntry.COLUMN_NAME_POSTER_PATH // Add this line
        )
        val cursor: Cursor = db.query(
            MovieDatabaseContract.MovieEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )
        val movies = mutableListOf<MovieResult>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(MovieDatabaseContract.MovieEntry.COLUMN_NAME_ID))
                val title = getString(getColumnIndexOrThrow(MovieDatabaseContract.MovieEntry.COLUMN_NAME_TITLE))
                val overview = getString(getColumnIndexOrThrow(MovieDatabaseContract.MovieEntry.COLUMN_NAME_OVERVIEW))
                val posterPath = getString(getColumnIndexOrThrow(MovieDatabaseContract.MovieEntry.COLUMN_NAME_POSTER_PATH)) // Add this line
                movies.add(MovieResult(id, title, overview, posterPath))
            }
        }
        cursor.close()
        return movies
    }
    override fun insertMovie(movie: MovieResult) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MovieDatabaseContract.MovieEntry.COLUMN_NAME_ID, movie.id)
            put(MovieDatabaseContract.MovieEntry.COLUMN_NAME_TITLE, movie.title)
            put(MovieDatabaseContract.MovieEntry.COLUMN_NAME_OVERVIEW, movie.overview)
            put(MovieDatabaseContract.MovieEntry.COLUMN_NAME_POSTER_PATH, movie.poster_path) // Add this line
        }
        db.insert(MovieDatabaseContract.MovieEntry.TABLE_NAME, null, values)
    }
}
// {
// [[], [], []]
//
// }


