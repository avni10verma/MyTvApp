package com.example.mytvapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.interfaces.MovieApiService
import com.example.data.interfaces.RetrofitInstance
import com.example.data.repository.MovieRepositoryImpl

class SearchFragment : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var recyclerViewSearchResults: RecyclerView
    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory(MovieRepositoryImpl(RetrofitInstance.instance.create(MovieApiService::class.java)))
    }

    private lateinit var moviePosterAdapter: MoviePosterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        searchView = view.findViewById(R.id.search_view)
        recyclerViewSearchResults = view.findViewById(R.id.recycler_view_search_results)

        recyclerViewSearchResults.layoutManager = GridLayoutManager(context, 3) // 3 columns in grid
        moviePosterAdapter = MoviePosterAdapter(emptyList())
        recyclerViewSearchResults.adapter = moviePosterAdapter

        setupSearchView()

        return view
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    movieViewModel.searchMovies(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.isNotEmpty()) {
                        movieViewModel.searchMovies(it)
                    }
                }
                return false
            }
        })

        //movieViewModel.searchMovies(query).observe(viewLifecycleOwner) { movies ->
          //  moviePosterAdapter.updateMovies(movies)
        //}
    }
}
