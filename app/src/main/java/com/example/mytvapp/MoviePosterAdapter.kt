package com.example.mytvapp

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviePosterAdapter(private var movieList: List<com.example.data.model.MovieResult>, private val aspectRatio: Float) : RecyclerView.Adapter<MoviePosterAdapter.PosterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_poster, parent, false)
        return PosterViewHolder(view)
    }

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        val movie = movieList[position]
        Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/w500${movie.poster_path}").into(holder.imageView)

        

        holder.imageView.setAspectRatio(aspectRatio)
        holder.itemView.setOnClickListener {
            Log.d("Onclick","The button is pressed")
            val context = holder.itemView.context
            val intent = Intent(context, MovieDetailActivity::class.java).apply {
                Log.d("movie","poster passed")
                putExtra("MOVIE_POSTER",movie.poster_path)

                Log.d("title","title passed")
                putExtra("MOVIE_TITLE", movie.title)

                Log.d("overview","overview passed")
                putExtra("MOVIE_OVERVIEW",movie.overview)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = movieList.size

    fun updateMovies(movies: List<com.example.data.model.MovieResult>) {
        this.movieList = movies
        notifyDataSetChanged()
    }

    class PosterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: AspectRatioImageView = itemView.findViewById(R.id.image_view_poster)
    }
}
