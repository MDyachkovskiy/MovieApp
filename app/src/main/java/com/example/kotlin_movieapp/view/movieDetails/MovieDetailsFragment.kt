package com.example.kotlin_movieapp.view.movieDetails

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.kotlin_movieapp.R
import com.example.kotlin_movieapp.databinding.FragmentMovieDetailBinding
import com.example.kotlin_movieapp.model.AppState.AppState
import com.example.kotlin_movieapp.model.datasource.domain.collection.Movie
import com.example.kotlin_movieapp.model.datasource.domain.movieDetail.MovieDetailsResponse
import com.example.kotlin_movieapp.model.datasource.domain.movieDetail.Person
import com.example.kotlin_movieapp.utils.KEY_BUNDLE_MOVIE
import com.example.kotlin_movieapp.utils.convert
import com.example.kotlin_movieapp.utils.init
import com.example.kotlin_movieapp.utils.showToast
import com.example.kotlin_movieapp.view.base.BaseFragmentWithAppState
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailsFragment : BaseFragmentWithAppState<AppState, MovieDetailsResponse, FragmentMovieDetailBinding>(
    FragmentMovieDetailBinding::inflate
)
{
    private lateinit var movieBundle : Movie
    private lateinit var movie : MovieDetailsResponse

    private val viewModel: MovieDetailsViewModel by viewModel()

    companion object {
        fun newInstance(bundle: Bundle) : MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movieDetail.visibility = View.VISIBLE

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

        binding.favorite.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked) {
                Thread {
                    saveFavoriteMovie(movie)
                }.start()
                binding.movieDetail.showToast(getString(R.string.addMovieToFavorite))
            } else {
                Thread {
                    deleteFavoriteMovie(movie)
                }.start()
                binding.movieDetail.showToast(getString(R.string.deleteMovieFromFavorite))
            }
        }

        movieBundle = arguments?.getParcelable(KEY_BUNDLE_MOVIE) ?: Movie()

        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        requestMovieDetail(movieBundle.id)
    }

    private fun requestMovieDetail(movieId: Int?) {
        viewModel.getMovieFromRemoteSource(movieId)
    }

    override fun setupData(data: MovieDetailsResponse) {
        movie = data
        displayMovie(data)
    }

    private fun displayMovie(movieDTO : MovieDetailsResponse) {

        Thread{
            val date = System.currentTimeMillis()
            saveMovie(movieDTO, date)
        }.start()

        with(binding) {

            movieDescription.text = movieDTO.description
            movieTitle.text = movieDTO.name

            view?.let {
                Glide.with(it).load(movieDTO.poster?.url).into(moviePoster) }

            initPersonsRecyclerView(movieDTO.persons)

            movieReleaseDate.text = movieDTO.year.toString()
            movieLength.text = getString(R.string.movieLength, movieDTO.movieLength.toString())
            movieBudget.text = getString(R.string.movieBudget,
                movieDTO.budget?.value?.toString(), movieDTO.budget?.currency)
            movieKpRating.text = movieDTO.rating?.kp.toString()
            movieImdbRating.text = movieDTO.rating?.imdb.toString()

            movieGenres.text = movieDTO.genres?.convert { genre -> genre.name }

            movieCountry.text = movieDTO.countries?.convert {country -> country.name}
        }
    }

    private fun initPersonsRecyclerView (persons: List <Person>) {
        val actors = persons.filter {
            it.enProfession == "actor"
        }
        val movieStaff = persons.filter{
            it.enProfession != "actor"
        }

        binding.actorsRV.init(PersonsAdapter(actors), LinearLayoutManager.HORIZONTAL)
        binding.movieStaffRV.init(PersonsAdapter(movieStaff), LinearLayoutManager.HORIZONTAL)
    }

    private fun saveMovie(movieDTO: MovieDetailsResponse, date: Long) {
        viewModel.saveMovieToDB(movieDTO, date)
    }

    private fun saveFavoriteMovie (movieDTO: MovieDetailsResponse) {
        viewModel.saveFavoriteMovieToDB(movieDTO)
    }

    private fun deleteFavoriteMovie (movieDTO: MovieDetailsResponse) {
        viewModel.deleteFavoriteMovieFromDB(movieDTO)
    }

    private fun addCommentToMovie(movieDTO: MovieDetailsResponse, text: Editable?){
        viewModel.addCommentToMovie(movieDTO, text)
    }
}