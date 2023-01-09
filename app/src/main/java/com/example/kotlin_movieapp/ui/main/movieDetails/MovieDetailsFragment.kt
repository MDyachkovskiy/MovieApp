package com.example.kotlin_movieapp.ui.main.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin_movieapp.R
import com.example.kotlin_movieapp.databinding.MovieDetailFragmentBinding
import com.example.kotlin_movieapp.models.Movie
import com.example.kotlin_movieapp.models.DTO.MovieDTO
import com.example.kotlin_movieapp.repository.MovieLoader
import com.example.kotlin_movieapp.utils.KEY_BUNDLE_MOVIE

class MovieDetailsFragment : Fragment() {

    private var _binding: MovieDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieBundle : Movie

    private val onLoadListener : MovieLoader.MovieLoaderListener =
        object : MovieLoader.MovieLoaderListener {
            override fun onLoaded(movieDTO: MovieDTO) {
                displayMovie(movieDTO)
            }

            override fun onFailed(throwable: Throwable) {
                TODO("Not yet implemented")
            }

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

        movieBundle = arguments?.getParcelable<Movie>(KEY_BUNDLE_MOVIE) ?: Movie()

        binding.movieDetail.visibility = View.GONE
        binding.loadingLayout.visibility = View.VISIBLE

        val loader = MovieLoader(movieBundle.id, onLoadListener)
        loader.loadMovie()

    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
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
}