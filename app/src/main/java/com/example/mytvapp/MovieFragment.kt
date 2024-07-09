package com.example.mytvapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.room.InvalidationTracker
import com.bumptech.glide.Glide
import com.example.data.interfaces.MovieApiService

class MovieFragment : Fragment() {

    private var moviePoster:String?=null
    private var movieTitle:String?=null
    private var movieOverview:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            moviePoster = it.getString("MOVIE_POSTER")
            movieTitle = it.getString("MOVIE_TITLE")
            movieOverview = it.getString("MOVIE_OVERVIEW")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.movie_detail_info, container, false)
        val titleTextView: TextView = view.findViewById(R.id.movie_title)
        val overviewTextView: TextView = view.findViewById(R.id.overview)
        val posterImageView: ImageView = view.findViewById(R.id.movie_detail_image)
        val watchButton: Button = view.findViewById(R.id.WatchNowbutton)

        titleTextView.text = movieTitle
        overviewTextView.text = movieOverview
        Log.d("MoviePoster","the poster is visble")
        val posterUrl = "https://image.tmdb.org/t/p/w500$moviePoster"

        Glide.with(this).load(posterUrl).into(posterImageView)
        Log.d("checking", "{$posterImageView}")

        watchButton.setOnClickListener {
            val fragment = ExoPlayerFragment.newInstance("https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8")
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_movie_container, fragment)
                ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                ?.addToBackStack(null)
                ?.commit()
        }
        return view
    }


    companion object {
        @JvmStatic
        fun newInstance() = MovieFragment().apply {
            arguments = Bundle().apply {
                // Add any arguments if necessary
            }
        }
    }
}

    
