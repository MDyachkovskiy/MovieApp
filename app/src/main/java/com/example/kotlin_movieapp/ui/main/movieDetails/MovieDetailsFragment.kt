package com.example.kotlin_movieapp.ui.main.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_movieapp.R
import com.example.kotlin_movieapp.databinding.MovieDetailFragmentBinding
import com.example.kotlin_movieapp.models.Movie
import com.example.kotlin_movieapp.models.DTO.MovieDTO
import com.example.kotlin_movieapp.ui.main.AppState
import com.example.kotlin_movieapp.utils.KEY_BUNDLE_MOVIE
import com.google.android.material.snackbar.Snackbar

class MovieDetailsFragment : Fragment() {

    private var _binding: MovieDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieBundle : Movie

    private val viewModel: MovieDetailsViewModel by lazy {
        ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
    }

    companion object {
        fun newInstance(bundle: Bundle) : MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = MovieDetailFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movieDetail.visibility = View.GONE
        binding.loadingLayout.visibility = View.VISIBLE

        movieBundle = arguments?.getParcelable<Movie>(KEY_BUNDLE_MOVIE) ?: Movie()

        viewModel.liveData.observe(viewLifecycleOwner, Observer{
                appState -> renderData(appState) })

        viewModel.loadMovie(movieBundle.id)
    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                binding.movieDetail.showSnackBar(
                    getString(R.string.data_loading_error),
                    0)
            }

            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }

            is AppState.SuccessMovieDetails -> {
                binding.loadingLayout.visibility = View.GONE
                displayMovie(appState.movieDTO)
                binding.movieDetail.showSnackBar(
                    getString(R.string.data_loading_success),
                    0)
            }
            else -> {
                binding.movieDetail.showSnackBar(
                    getString(R.string.critical_error),
                    0)
            }
        }
    }

    private fun displayMovie(movieDTO : MovieDTO) {
        with(binding) {
            movieDetail.visibility = View.VISIBLE
            loadingLayout.visibility = View.GONE

            movieDescription.text = movieDTO.description
            movieTitle.text = movieDTO.name
            moviePoster.setImageResource(movieBundle.image)
            movieReleaseDate.text = movieDTO.year.toString()
            movieLength.text = getString(R.string.movieLength, movieDTO.movieLength.toString())
            movieBudget.text = getString(R.string.movieBudget,
                movieDTO.budget?.value?.toString(), movieDTO.budget?.currency);
            movieKpRating.text = movieDTO.rating?.kp.toString()
            movieImdbRating.text = movieDTO.rating?.imdb.toString()
            movieGenres.text = movieDTO.genres?.joinToString(", ")
            movieCountry.text = movieDTO.countries?.joinToString(", ")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun View.showSnackBar(
        text: String,
        length: Int = Snackbar.LENGTH_INDEFINITE,
    ) {
        Snackbar.make(this, text, length).show()
    }

}