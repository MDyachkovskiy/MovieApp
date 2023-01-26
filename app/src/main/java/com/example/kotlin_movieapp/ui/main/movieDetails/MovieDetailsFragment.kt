package com.example.kotlin_movieapp.ui.main.movieDetails

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.kotlin_movieapp.R
import com.example.kotlin_movieapp.databinding.MovieDetailFragmentBinding
import com.example.kotlin_movieapp.model.collectionResponse.CollectionItem
import com.example.kotlin_movieapp.model.movieDetailsResponse.MovieDTO
import com.example.kotlin_movieapp.ui.main.DetailsState
import com.example.kotlin_movieapp.utils.KEY_BUNDLE_MOVIE
import com.example.kotlin_movieapp.utils.showSnackBar

class MovieDetailsFragment : Fragment() {

    private var _binding: MovieDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieBundle : CollectionItem
    private lateinit var movie : MovieDTO

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

        binding.movieDetail.visibility = View.VISIBLE
        binding.loadingLayout.visibility = View.VISIBLE

        binding.userNote.addTextChangedListener (object : TextWatcher{
            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(text: Editable?) {
              Thread{
                  addCommentToMovie(movie, text)
              }.start()

            }

        })

        movieBundle = arguments?.getParcelable(KEY_BUNDLE_MOVIE) ?: CollectionItem()

        viewModel.getLiveData().observe(viewLifecycleOwner, Observer{
               renderData(it)
        })

        requestMovieDetail(movieBundle.kinopoiskId)
    }

    private fun requestMovieDetail(movieId: Int?) {
        viewModel.getMovieFromRemoteSource(movieId)
    }

    private fun renderData(appState: DetailsState) {
        when (appState) {
            is DetailsState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                binding.movieDetail.showSnackBar(
                    getString(R.string.data_loading_error),
                    getString(R.string.reload),
                    {
                        viewModel.getMovieFromRemoteSource(movieBundle.kinopoiskId)
                    },
                    0)
            }

            is DetailsState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }

            is DetailsState.Success -> {
                binding.movieDetail.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE
                movie = appState.movieDTO
                displayMovie(appState.movieDTO)
                binding.movieDetail.showSnackBar(
                    getString(R.string.data_loading_success),
                    0)
            }
        }
    }

    private fun displayMovie(movieDTO : MovieDTO) {

        Thread{
            val date = System.currentTimeMillis()
            saveMovie(movieDTO, date)
        }.start()

        with(binding) {
            movieDetail.visibility = View.VISIBLE
            loadingLayout.visibility = View.GONE

            movieDescription.text = movieDTO.description
            movieTitle.text = movieDTO.name

            view?.let {
                Glide.with(it).load(movieDTO.poster?.url).into(moviePoster) }

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

    private fun saveMovie(movieDTO: MovieDTO, date: Long) {
        viewModel.saveMovieToDB(movieDTO, date)
    }

    private fun addCommentToMovie(movieDTO: MovieDTO, text: Editable?){
        viewModel.addCommentToMovie(movieDTO, text)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}