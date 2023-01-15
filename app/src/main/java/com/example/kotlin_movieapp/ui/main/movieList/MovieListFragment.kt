package com.example.kotlin_movieapp.ui.main.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_movieapp.R
import com.example.kotlin_movieapp.adapters.MovieAdapter
import com.example.kotlin_movieapp.adapters.MovieServiceAdapter
import com.example.kotlin_movieapp.databinding.MovieListFragmentBinding
import com.example.kotlin_movieapp.models.collectionResponse.Top250Response
import com.example.kotlin_movieapp.ui.main.AppState
import com.google.android.material.snackbar.Snackbar

class MovieListFragment : Fragment() {

    private var _binding: MovieListFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MovieListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = MovieListFragmentBinding.inflate(inflater, container, false)

        return binding.root

    }

    private val viewModel: MovieListViewModel by lazy {
        ViewModelProvider(this).get(MovieListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loadingLayout.visibility = View.VISIBLE

        viewModel.getData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })

        viewModel.getTop250Collection()

    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                binding.main.showSnackBar(
                    getString(R.string.data_loading_error),
                    0)
            }

            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }

            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                initRV(appState.movieData)
                binding.main.showSnackBar(
                    getString(R.string.data_loading_success),
                    0)
            }
        }
    }

    private fun initRV(data: Top250Response) {

        val movieList = data.top250Movies

        binding.RVTopMovies.apply {
            adapter = MovieAdapter(movieList)
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        binding.RVSerials.apply {
            adapter = MovieServiceAdapter(movieList)
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        binding.RVNewMovies.apply {
            adapter = MovieAdapter(movieList)
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }

    private fun View.showSnackBar(
        text: String,
        length: Int = Snackbar.LENGTH_INDEFINITE,
    ) {
        Snackbar.make(this, text, length).show()
    }
}