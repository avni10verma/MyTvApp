package com.example.mytvapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.interfaces.MovieApiService
import com.example.data.repository.MovieRepositoryImpl

class SearchFragment : Fragment() {

    private lateinit var searchView: EditText
    private lateinit var recyclerViewSearchResults: RecyclerView
    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory(MovieRepositoryImpl(MovieApiService.create()))
    }

    private lateinit var moviePosterAdapter: MoviePosterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        searchView = view.findViewById(R.id.search_view)
        recyclerViewSearchResults = view.findViewById(R.id.recycler_view_search_results)

        recyclerViewSearchResults.layoutManager = GridLayoutManager(context, 5) // 3 columns in grid
        moviePosterAdapter = MoviePosterAdapter(emptyList())
        recyclerViewSearchResults.adapter = moviePosterAdapter

        setupSearchView()

        return view
    }

    private fun setupSearchView() {
        searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed here
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No action needed here

            }

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    Log.d("Check", "${s}")
                    if (it.isNotEmpty()) {
                        movieViewModel.searchDebounced(it.toString())
                        movieViewModel.searchMovies(it.toString()).observe(viewLifecycleOwner) { movies ->
                            moviePosterAdapter.updateMovies(movies)
                        }
                    }
                }
            }
        })
    }
}