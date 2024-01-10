package com.test.application.movie_details.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.google.android.material.tabs.TabLayoutMediator
import com.test.application.core.domain.movieDetail.MovieDetails
import com.test.application.core.navigation.BackPressedHandler
import com.test.application.core.utils.AppState.AppState
import com.test.application.core.utils.KEY_BUNDLE_MOVIE
import com.test.application.core.view.BaseFragmentWithAppState
import com.test.application.movie_details.R
import com.test.application.movie_details.adapter.ViewPagerAdapter
import com.test.application.movie_details.databinding.FragmentMovieDetailsNewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

@AndroidEntryPoint
class MovieDetailsFragmentNew: BaseFragmentWithAppState<AppState, MovieDetails, FragmentMovieDetailsNewBinding>(
    FragmentMovieDetailsNewBinding::inflate
) {

    private val movieId: Int by lazy {
        arguments?.getInt(KEY_BUNDLE_MOVIE) ?:
        throw IllegalArgumentException(getString(R.string.incorrect_movie_id))
    }

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
    }

    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        requestMovieDetail(movieId)
    }

    private fun requestMovieDetail(movieId: Int) {
        viewModel.getMovieFromRemoteSource(movieId)
    }
    override fun setupData(data: MovieDetails) {
        displayMovie(data)
    }

    private fun displayMovie(movie: MovieDetails) {
        val date = System.currentTimeMillis()
        saveMovie(movie, date)
        initMoviePosterImage(movie)
        initTextData(movie)
        setupViewPagerAndTabs()
    }

    private fun setupViewPagerAndTabs() {
        val pagerAdapter = ViewPagerAdapter(this)
        binding.movieInfoViewPager.adapter = pagerAdapter
        binding.movieInfoViewPager.disableSwipe()

        TabLayoutMediator(binding.tabLayout, binding.movieInfoViewPager) { tab, position ->
            tab.text = pagerAdapter.tabTitles[position]
        }.attach()
    }

    private fun ViewPager2.disableSwipe() {
        this.isUserInputEnabled = false
    }

    private fun initTextData(movie: MovieDetails) {
        with(binding) {
            tvMovieTitle.text = movie.name

            chipMovieDuration.text = if(movie.movieLength?.let { it > 0 } == true) {
                getString(R.string.format_movie_length, movie.movieLength.toString())
            } else { "" }

            chipReleaseDate.text = movie.premiere?.world?.let { reformatDate(it) }
        }
    }

    private fun reformatDate(dateStr: String): String {
        return try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.")
            val date = LocalDate.parse(dateStr, formatter)
            date.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.getDefault()))
        } catch(e: DateTimeParseException) {
            getString(R.string.undefined_date)
        }
    }

    private fun initMoviePosterImage(movie: MovieDetails) {
        binding.moviePoster.load(movie.movieDetailsPoster?.url){
            crossfade(true)
            placeholder(com.test.application.core.R.drawable.default_placeholder)
        }

        val backdropUrl = movie.backdrop?.url
        if (backdropUrl != null) {
            binding.backgroundImage.visibility = View.VISIBLE
            updateMoviePosterBlockLayout(true)
            binding.backgroundImage.load(backdropUrl) {
                crossfade(true)
                listener(onError = {_,_ ->
                    binding.backgroundImage.visibility = View.GONE
                    updateMoviePosterBlockLayout(false)
                })
            }
        } else {
            binding.backgroundImage.visibility = View.GONE
            updateMoviePosterBlockLayout(false)
        }
    }

    private fun updateMoviePosterBlockLayout(isBackgroundVisible: Boolean) {
        val layoutParams = binding.moviePosterBlock.layoutParams as ConstraintLayout.LayoutParams

        if (isBackgroundVisible) {
            layoutParams.topToTop = ConstraintLayout.LayoutParams.UNSET
            layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            layoutParams.bottomToBottom = binding.chipReleaseDate.id
            layoutParams.marginStart = resources
                .getDimensionPixelSize(com.test.application.core.R.dimen.margin_24dp_medium)
        } else {
            layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.UNSET
            layoutParams.setMargins(
                resources
                    .getDimensionPixelSize(com.test.application.core.R.dimen.margin_24dp_medium),
                resources
                    .getDimensionPixelSize(com.test.application.core.R.dimen.margin_16dp_medium),
                0,
                0
            )
        }

        binding.moviePosterBlock.layoutParams = layoutParams
    }

    private fun saveMovie(movie: MovieDetails, date: Long) {
        viewModel.saveMovieToDB(movie, date)
    }
}