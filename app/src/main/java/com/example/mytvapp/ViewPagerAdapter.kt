// ViewPagerAdapter.kt
package com.example.mytvapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.data.model.MovieResult

class ViewPagerAdapter(
    private val context: Context,
    private var movieList: List<List<MovieResult>>) : PagerAdapter() {

    override fun getCount(): Int = movieList.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view_pager, container, false)
        val imageView: ImageView = view.findViewById(R.id.image_view)
        val movie = movieList[position]
        Glide.with(context).load("https://image.tmdb.org/t/p/w500${movie[1].backdrop_path}").into(imageView)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun updateMovies(movies: List<List<MovieResult>>) {
        this.movieList = movies
        notifyDataSetChanged()
    }
}