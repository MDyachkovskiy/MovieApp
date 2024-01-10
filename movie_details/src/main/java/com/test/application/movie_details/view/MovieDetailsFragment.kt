package com.test.application.movie_details.view

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.test.application.core.domain.movieDetail.MovieDetails
import com.test.application.core.domain.movieDetail.Person
import com.test.application.core.navigation.BackPressedHandler
import com.test.application.core.navigation.Navigator
import com.test.application.core.utils.AppState.AppState
import com.test.application.core.utils.KEY_BUNDLE_MOVIE
import com.test.application.core.utils.KEY_BUNDLE_PERSON
import com.test.application.core.utils.convert
import com.test.application.core.utils.init
import com.test.application.core.view.BaseFragmentWithAppState
import com.test.application.movie_details.R
import com.test.application.movie_details.adapter.PersonsAdapter
import com.test.application.movie_details.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsFragment : BaseFragmentWithAppState<AppState, MovieDetails, FragmentMovieDetailBinding>(
    FragmentMovieDetailBinding::inflate
) {
    private val movieId: Int by lazy {
        arguments?.getInt(KEY_BUNDLE_MOVIE) ?:
        throw IllegalArgumentException(getString(R.string.incorrect_movie_id))
    }
    private lateinit var movie: MovieDetails

    private val viewModel: MovieDetailsViewModel by viewModels()

    private var backPressedHandler: BackPressedHandler? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is BackPressedHandler) {
            backPressedHandler = context
        } else {
            throw RuntimeException(context.getString(R.string.error_back_pressed_handler))
        }
    }

    override fun onDetach() {
        super.onDetach()
        backPressedHandler = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPress()
    }

    private fun handleBackPress() {
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                backPressedHandler?.onBackButtonPressedInMovieDetails()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initCommentsEditTextButton()
        initFavoritesCheckBox(movieId)
    }

    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        requestMovieDetail(movieId)
    }

    private fun initCommentsEditTextButton() {
        binding.etUserNote.apply {
            setRawInputType(
                InputType.TYPE_CLASS_TEXT or
                        InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD or
                        InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
            )
            imeOptions = EditorInfo.IME_ACTION_DONE

            setOnEditorActionListener { textView, actionId, event ->
                if (event == null && actionId == EditorInfo.IME_ACTION_DONE) {
                    textView.clearFocus()
                    val imm = context
                        .getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(windowToken, 0)
                    val comment = textView.text.toString()
                    addCommentToMovie(movie, comment)
                }
                false
            }
        }
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
        binding.moviePoster.load(movie.movieDetailsPoster?.url){
            crossfade(true)
            placeholder(com.test.application.core.R.drawable.default_placeholder)
        }
    }

    private fun initPersonsRecyclerView(persons: List<Person>) {
        initActorsRV(persons)
        initMovieStaff(persons)
    }

    private fun initMovieStaff(persons: List<Person>) {
        val movieStaff = persons.filter {
            it.enProfession != "actor"
        }
        val movieStaffAdapter = PersonsAdapter(movieStaff)
        movieStaffAdapter.listener = {personId ->
            val bundle = bundleOf(KEY_BUNDLE_PERSON to personId)
            (activity as Navigator).navigateToPersonDetailsFragment(bundle)
        }
        binding.rvMovieStaff.init(PersonsAdapter(movieStaff), LinearLayoutManager.HORIZONTAL)
    }

    private fun initActorsRV(persons: List<Person>) {
        val actors = persons.filter {
            it.enProfession == "actor"
        }
        val actorsAdapter = PersonsAdapter(actors)
        actorsAdapter.listener = { personId ->
            val bundle = bundleOf(KEY_BUNDLE_PERSON to personId)
            (activity as Navigator).navigateToPersonDetailsFragment(bundle)
        }
        binding.rvActors.init(actorsAdapter, LinearLayoutManager.HORIZONTAL)
    }

    private fun saveMovie(movie: MovieDetails, date: Long) {
        viewModel.saveMovieToDB(movie, date)
    }

    private fun saveFavoriteMovie(movie: MovieDetails) {
        val date = System.currentTimeMillis()
        viewModel.saveFavoriteMovieToDB(movie, date)
    }

    private fun deleteFavoriteMovie(movie: MovieDetails) {
        viewModel.deleteFavoriteMovieFromDB(movie)
    }

    private fun addCommentToMovie(movie: MovieDetails, comment: String) {
        viewModel.addCommentToMovie(movie, comment)
    }

    private fun setFavoriteCheckBoxListener() {
        binding.favorite.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                saveFavoriteMovie(movie)
                val comment = getCommentFromEditText()
                addCommentToMovie(movie, comment)
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

    private fun getCommentFromEditText() : String {
        return binding.etUserNote.text.toString()
    }
}
