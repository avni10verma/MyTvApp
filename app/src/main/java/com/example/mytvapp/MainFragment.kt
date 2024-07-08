// MainFragment.kt
package com.example.mytvapp

import com.example.data.model.MovieResult
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.data.interfaces.MovieApiService
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainFragment : Fragment() {

        private lateinit var viewPager: ViewPager
        private lateinit var containerLayout: LinearLayout

        private val movieViewModel: MovieViewModel by viewModels {
            MovieViewModelFactory(com.example.data.repository.MovieRepositoryImpl(MovieApiService.create()))
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_main, container, false)
            viewPager = view.findViewById(R.id.view_pager_movie_banners)
            containerLayout = view.findViewById(R.id.container_layout)
            val viewPagerAdapter = ViewPagerAdapter(requireContext(), emptyList())
            viewPager.adapter = viewPagerAdapter


            // Observe top-rated movies and update ViewPager
            movieViewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
                val topRatedMovies = movies.take(5)
                topRatedMovies.map { (movie) ->
                    Log.d("avni", "$movie")
                }

                viewPagerAdapter.updateMovies(topRatedMovies)
            })


            val categories = listOf(
                "Continue Watching",
                "Popular Movies",
                "Upcoming Movies",
                "Trending Movies"
            )

            movieViewModel.movies.observe(viewLifecycleOwner) { movies ->
                categories.mapIndexed {index, category ->
                        Log.d("Avni", "${category}")
                    addCategoryView(category, movies[index])
                }
            }
            return view
        }

        private fun addCategoryView(title: String, movies: List<MovieResult>) {
            Log.d("Avniii", "${title}")

            movies.map { movie ->
                Log.d("$title","${movie.title}")
            }
            val categoryView = LayoutInflater.from(context).inflate(R.layout.item_movie_category, containerLayout, false)

            val textCategoryTitle: TextView = categoryView.findViewById(R.id.text_view_category)
            textCategoryTitle.text = title


            val recyclerViewPosters: RecyclerView = categoryView.findViewById(R.id.recycler_view_posters)
            recyclerViewPosters.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            val moviePosterAdapter = MoviePosterAdapter(movies)
            recyclerViewPosters.adapter = moviePosterAdapter

//             Apply item decoration
 //           val spacing = resources.getDimensionPixelSize(R.dimen.spacing_between_items)
//            val spacingDecoration = SpacingItemDecoration(spacing)
//            recyclerViewPosters.addItemDecoration(spacingDecoration)
//
            containerLayout.addView(categoryView)
        }
    }