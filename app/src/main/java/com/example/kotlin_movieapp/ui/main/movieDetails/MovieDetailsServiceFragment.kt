package com.example.kotlin_movieapp.ui.main.movieDetails

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.kotlin_movieapp.R
import com.example.kotlin_movieapp.databinding.MovieDetailFragmentBinding
import com.example.kotlin_movieapp.models.Movie
import com.example.kotlin_movieapp.models.DTO.MovieDTO
import com.example.kotlin_movieapp.utils.*

class MovieDetailsServiceFragment : Fragment() {

    private var _binding: MovieDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieBundle : Movie

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                intent.getParcelableExtra<MovieDTO>(KEY_SERVICE_TO_BR_MOVIE)?.let{
                    displayMovie(it)
                }
            }
        }
    }

    companion object {
        fun newInstance(bundle: Bundle) : MovieDetailsServiceFragment {
            val fragment = MovieDetailsServiceFragment()
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

        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(receiver,
        IntentFilter(WAVE_SERVICE_BROADCAST))

        movieBundle = arguments?.getParcelable(KEY_BUNDLE_MOVIE) ?: Movie()

        binding.movieDetail.visibility = View.GONE
        binding.loadingLayout.visibility = View.VISIBLE

        requireActivity().startService(Intent(requireContext(),MovieDetailsService::class.java).apply{
            putExtra(KEY_SERVICE_MOVIE_ID, movieBundle.id)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(receiver)
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