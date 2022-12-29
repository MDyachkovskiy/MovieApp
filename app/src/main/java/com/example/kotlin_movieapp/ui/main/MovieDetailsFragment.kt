package com.example.kotlin_movieapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin_movieapp.databinding.MovieDetailFragmentBinding
import com.example.kotlin_movieapp.models.Movie
import com.example.kotlin_movieapp.utils.KEY_BUNDLE_MOVIE

class MovieDetailsFragment : Fragment() {

    private var _binding: MovieDetailFragmentBinding? = null
    private val binding
        get() = _binding!!

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

        arguments?.getParcelable<Movie>(KEY_BUNDLE_MOVIE)?.let {
            renderData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun renderData(movie : Movie){
        with (binding) {
            movieDescription.text = movie.description
            movieTitle.text = movie.name
            moviePoster.setImageResource(movie.image)
        }
    }

}