package com.example.mytvapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviePosterAdapter(
    private var movieList: List<com.example.data.model.MovieResult>) : RecyclerView.Adapter<MoviePosterAdapter.PosterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_poster, parent, false)
        return PosterViewHolder(view)
    }

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        val movie = movieList[position]
        Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/w500${movie.poster_path}").into(holder.imageView)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, MovieDetailActivity::class.java).apply {
                putExtra("MOVIE_TITLE", movie.title)
                putExtra("MOVIE_URL", "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8")
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
        val imageView: ImageView = itemView.findViewById(R.id.image_view_poster)
    }
}
