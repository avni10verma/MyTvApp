import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.interfaces.MovieApiService
import com.example.data.repository.MovieRepositoryImpl
import com.example.mytvapp.MoviePosterAdapter
import com.example.mytvapp.MovieViewModel
import com.example.mytvapp.MovieViewModelFactory
import com.example.mytvapp.R


class SearchFragment : Fragment() {

    private lateinit var searchView: EditText
    private lateinit var recyclerViewSearchResults: RecyclerView
    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory(com.example.data.repository.MovieRepositoryImpl(MovieApiService.create()))
    }

    private lateinit var moviePosterAdapter: MoviePosterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        searchView = view.findViewById(R.id.search_view)
        recyclerViewSearchResults = view.findViewById(R.id.recycler_view_search_results)

        recyclerViewSearchResults.layoutManager = GridLayoutManager(context, 5)
        moviePosterAdapter = MoviePosterAdapter(emptyList(),16f/9f)
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
                    if (it.isNotEmpty()) {
                        movieViewModel.searchDebounced(it.toString())
                        movieViewModel.searchResults.observe(viewLifecycleOwner, Observer { movies ->
                            moviePosterAdapter.updateMovies(movies)
                        })
                    }
                }
            }
        })



    }
}
