package com.test.application.movie_details

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.test.application.core.utils.AppState.AppState
import com.test.application.core.domain.collection.Movie
import com.test.application.core.domain.movieDetail.MovieDetails
import com.test.application.core.domain.movieDetail.Person
import com.test.application.core.utils.KEY_BUNDLE_MOVIE
import com.test.application.core.utils.convert
import com.test.application.core.utils.init
import com.test.application.movie_details.databinding.FragmentMovieDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailsFragment : com.test.application.core.view.BaseFragmentWithAppState<AppState, MovieDetails, FragmentMovieDetailBinding>(
    FragmentMovieDetailBinding::inflate
) {
    private lateinit var movieBundle: Movie
    private lateinit var movie: MovieDetails

    private val viewModel: MovieDetailsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieBundle = arguments?.getParcelable(KEY_BUNDLE_MOVIE) ?: Movie()

        initViewModel()
        initCommentsEditText()
        initFavoritesCheckBox(movieBundle.id)
    }

    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        requestMovieDetail(movieBundle.id)
    }

    private fun initCommentsEditText() {
        binding.etUserNote.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(text: Editable?) {
                addCommentToMovie(movie, text)
            }
        })
    }

    private fun initFavoritesCheckBox(movieId: Int) {
        viewModel.checkFavoriteMovie(movieId)
        viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            binding.favorite.setOnCheckedChangeListener(null)
            binding.favorite.isChecked = isFavorite
            setFavoriteCheckBoxListener()
        }
    }

    private fun requestMovieDetail(movieId: Int) {
        viewModel.getMovieFromRemoteSource(movieId)
    }

    override fun setupData(data: MovieDetails) {
        movie = data
        displayMovie(data)
    }

    private fun displayMovie(movie: MovieDetails) {
        val date = System.currentTimeMillis()
        saveMovie(movie, date)
        initMoviePosterImage(movie)
        initTextData(movie)
        initPersonsRecyclerView(movie.persons)
    }

    private fun initTextData(movie: MovieDetails) {
        with(binding) {
            tvMovieDescription.text = movie.description
            tvMovieTitle.text = movie.name
            tvMovieReleaseDate.text = movie.year.toString()

            tvMovieLength.text = getString(
                R.string.format_movie_length, movie.movieLength.toString())

            tvMovieBudget.text = getString(
                R.string.format_movie_budget,
                movie.budget?.value?.toString(), movie.budget?.currency
            )
            tvMovieKpRating.text = movie.rating?.kp.toString()
            tvMovieImdbRating.text = movie.rating?.imdb.toString()

            tvMovieGenres.text = movie.genres?.convert { genre -> genre.name }

            tvMovieCountry.text = movie.countries?.convert { country -> country.name }
        }
    }

    private fun initMoviePosterImage(movie: MovieDetails) {
        binding.moviePoster.load(movie.poster?.url){
            crossfade(true)
            placeholder(com.test.application.core.R.drawable.default_placeholder)
        }
    }

    private fun initPersonsRecyclerView(persons: List<Person>) {
        val actors = persons.filter {
            it.enProfession == "actor"
        }
        val movieStaff = persons.filter {
            it.enProfession != "actor"
        }

        binding.rvActors.init(PersonsAdapter(actors), LinearLayoutManager.HORIZONTAL)
        binding.rvMovieStaff.init(PersonsAdapter(movieStaff), LinearLayoutManager.HORIZONTAL)
    }

    private fun saveMovie(movie: MovieDetails, date: Long) {
        viewModel.saveMovieToDB(movie, date)
    }

    private fun saveFavoriteMovie(movie: MovieDetails) {
        viewModel.saveFavoriteMovieToDB(movie)
    }

    private fun deleteFavoriteMovie(movie: MovieDetails) {
        viewModel.deleteFavoriteMovieFromDB(movie)
    }

    private fun addCommentToMovie(movie: MovieDetails, text: Editable?) {
        viewModel.addCommentToMovie(movie, text)
    }

    private fun setFavoriteCheckBoxListener() {
        binding.favorite.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                saveFavoriteMovie(movie)
                binding.movieDetail
                    .showToast(getString(com.test.application.core.R.string.add_movie_to_favorite))
            } else {
                deleteFavoriteMovie(movie)
                binding.movieDetail
                    .showToast(getString(com.test.application.core.R.string.delete_movie_from_favorite))
            }
        }
    }
    private fun View.showToast(text: String) {
        Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
    }
}
