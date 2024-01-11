package com.test.application.movie_details.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import coil.load
import com.google.android.material.tabs.TabLayoutMediator
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.test.application.core.domain.movieDetail.MovieDetails
import com.test.application.core.navigation.BackPressedHandler
import com.test.application.core.utils.AppState.AppState
import com.test.application.core.utils.KEY_BUNDLE_MOVIE
import com.test.application.core.view.BaseFragmentWithAppState
import com.test.application.movie_details.R
import com.test.application.movie_details.adapter.ViewPagerAdapter
import com.test.application.movie_details.databinding.FragmentMovieDetailsNewBinding
import com.test.application.movie_details.navigation.TrailerPlayListener
import com.test.application.movie_details.utils.disableSwipe
import com.test.application.movie_details.utils.extractVideoIdFromUrl
import com.test.application.movie_details.utils.reformatDate
import com.test.application.movie_details.utils.reformatVotes
import dagger.hilt.android.AndroidEntryPoint
import java.math.BigDecimal
import java.math.RoundingMode

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
                listener(onError = { _, _ ->
                    updateBackgroundVisibility(false)
                })
            }
        }
    }

    private fun updateBackgroundVisibility(isVisible: Boolean) {
        binding.backgroundImage.visibility = if (isVisible) View.VISIBLE else View.GONE
        binding.gradientView.visibility = binding.backgroundImage.visibility
        updateMoviePosterBlockLayout(isVisible)
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

    override fun onTrailerClicked(videoUrl: String) {
        val youTubePlayerView = binding.videoContainer.youtubePlayerView
        lifecycle.addObserver(youTubePlayerView)

        binding.videoContainer.root.visibility = View.VISIBLE

        val videoId = extractVideoIdFromUrl(videoUrl)
        var currentYouTubePlayer: YouTubePlayer? = null

        youTubePlayerView.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                currentYouTubePlayer = youTubePlayer
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })

        binding.videoContainer.closeButton.setOnClickListener {
            currentYouTubePlayer?.pause()
            binding.videoContainer.root.visibility = View.GONE
        }
    }
}