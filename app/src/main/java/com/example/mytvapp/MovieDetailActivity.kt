package com.example.mytvapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val moviePoster = intent.getStringExtra("MOVIE_POSTER")
        val movieTitle = intent.getStringExtra("MOVIE_TITLE")
        val movieOverview = intent.getStringExtra("MOVIE_OVERVIEW")

        if (savedInstanceState == null) {

            val fragment = MovieFragment().apply {
                arguments = Bundle().apply {
                    Log.d("fragment","title passed to fragment")
                    putString("MOVIE_TITLE", movieTitle)

                    putString("MOVIE_OVERVIEW", movieOverview)
                    putString("MOVIE_POSTER", moviePoster)
                }
            }
            supportFragmentManager.commit {
                Log.d("Transaction", "The transaction occured")
                replace(R.id.fragment_movie_container, fragment)
            }
        }
    }


}
