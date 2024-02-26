package com.test.application.movie_details.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
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
import com.test.application.movie_details.navigation.TrailerPlayListener
import com.test.application.movie_details.utils.TrailerPlayerManager
import com.test.application.movie_details.utils.disableSwipe
import com.test.application.movie_details.utils.reformatDate
import com.test.application.movie_details.utils.reformatVotes
import dagger.hilt.android.AndroidEntryPoint
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.abs

@AndroidEntryPoint
class MovieDetailsFragmentNew :
    BaseFragmentWithAppState<AppState, MovieDetails, FragmentMovieDetailsNewBinding>(
        FragmentMovieDetailsNewBinding::inflate
    ), TrailerPlayListener {

    private val movieId: Int by lazy {
        arguments?.getInt(KEY_BUNDLE_MOVIE)
            ?: throw IllegalArgumentException(getString(R.string.incorrect_movie_id))
    }

    private val viewModel: MovieDetailsViewModel by viewModels()

    private var backPressedHandler: BackPressedHandler? = null
    private var trailerPlayerManager: TrailerPlayerManager? = null
    private var tabLayoutMediator: TabLayoutMediator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BackPressedHandler) {
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

    private fun setupCollapsedToolbar() {
        val appBarLayout = binding.abbBarLayout
        val toolbar = binding.toolbar

        appBarLayout.addOnOffsetChangedListener { _, verticalOffset ->
            if (abs(verticalOffset) == appBarLayout.totalScrollRange) {
                toolbar.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        com.test.application.core.R.color.black
                    )
                )
            } else {
                toolbar.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        android.R.color.transparent
                    )
                )
            }
        }
    }

    private fun handleBackPress() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backPressedHandler?.onBackButtonPressedInMovieDetails()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setupCollapsedToolbar()
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

        tabLayoutMediator = TabLayoutMediator(binding.tabLayout, binding.movieInfoViewPager) { tab, position ->
            tab.text = pagerAdapter.tabTitles[position]
        }.also { it.attach() }
    }

    private fun initTextData(movie: MovieDetails) {
        with(binding) {
            tvMovieTitle.text = movie.name

            chipMovieDuration.text = if (movie.movieLength?.let { it > 0 } == true) {
                getString(R.string.format_movie_length, movie.movieLength.toString())
            } else {
                ""
            }

            chipReleaseDate.text = movie.premiere?.world?.let { reformatDate(requireContext(), it) }

            tvKpRating.text = movie.rating?.kp?.let { rating ->
                BigDecimal(rating).setScale(1, RoundingMode.HALF_UP).toString()
            }
            tvTmdbRating.text = movie.rating?.imdb.toString()

            tvKpVotesCount.text = reformatVotes(movie.votes?.kp)
            tvTmdbVotesCount.text = reformatVotes(movie.votes?.imdb)
        }
    }

    private fun initMoviePosterImage(movie: MovieDetails) {
        binding.moviePoster.load(movie.movieDetailsPoster?.url) {
            crossfade(true)
            placeholder(com.test.application.core.R.drawable.default_placeholder)
        }
        updateBackgroundImage(movie.backdrop?.url)
    }

    private fun updateBackgroundImage(url: String?) {
        val isVisible = url != null
        updateBackgroundVisibility(isVisible)

        if (isVisible) {
            binding.backgroundImage.load(url) {
                crossfade(true)
            }
        }
    }

    private fun updateBackgroundVisibility(isVisible: Boolean) {
        binding.backgroundImage.visibility = if (isVisible) View.VISIBLE else View.GONE
        binding.gradientView.visibility = binding.backgroundImage.visibility
    }

    private fun saveMovie(movie: MovieDetails, date: Long) {
        viewModel.saveMovieToDB(movie, date)
    }

    override fun onTrailerClicked(videoUrl: String) {
        trailerPlayerManager = TrailerPlayerManager(
            lifecycle = viewLifecycleOwner.lifecycle,
            youTubePlayerView = binding.videoContainer.youtubePlayerView,
            videoContainerView = binding.videoContainer.root,
        )
        trailerPlayerManager?.playTrailer(videoUrl)

        binding.videoContainer.closeButton.setOnClickListener {
            trailerPlayerManager?.cleanup()
            binding.videoContainer.root.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        tabLayoutMediator?.detach()
        tabLayoutMediator = null
        binding.movieInfoViewPager.adapter = null
        trailerPlayerManager?.cleanup()
        trailerPlayerManager = null
        super.onDestroyView()
    }
}