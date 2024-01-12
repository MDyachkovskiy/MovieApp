package com.test.application.movie_details.view

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.google.android.material.chip.Chip
import com.test.application.core.domain.movieDetail.Genre
import com.test.application.core.domain.movieDetail.MovieDetails
import com.test.application.core.domain.movieDetail.Person
import com.test.application.core.domain.movieDetail.Trailer
import com.test.application.core.navigation.Navigator
import com.test.application.core.utils.AppState.AppState
import com.test.application.core.utils.KEY_BUNDLE_PERSON
import com.test.application.core.utils.convert
import com.test.application.core.view.BaseFragmentWithAppState
import com.test.application.movie_details.R
import com.test.application.movie_details.adapter.MovieStaffAdapter
import com.test.application.movie_details.adapter.TrailerAdapter
import com.test.application.movie_details.databinding.FragmentMovieInfoBinding
import com.test.application.movie_details.navigation.TrailerPlayListener
import com.test.application.movie_details.utils.MILLISECONDS_PER_INCH
import com.test.application.movie_details.utils.reformatBudget
import com.test.application.movie_details.utils.reformatPremiereDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieInfoFragment : BaseFragmentWithAppState<AppState, MovieDetails, FragmentMovieInfoBinding>(
    FragmentMovieInfoBinding::inflate
) {

    private var trailerPlayListener: TrailerPlayListener? = null

    private val viewModel: MovieDetailsViewModel by viewModels({requireParentFragment()})

    override fun onAttach(context: Context) {
        super.onAttach(context)
        trailerPlayListener = parentFragment as? TrailerPlayListener
    }

    override fun onDetach() {
        super.onDetach()
        trailerPlayListener = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    override fun setupData(data: MovieDetails) {
        displayTextInfo(data)
        initGenresChip(data.genres)
        initMovieStaffList(data.persons)
        initTrailersList(data.trailers)
        setupExpandButton()
    }

    private fun setupExpandButton() {
        val tvDescription = binding.tvMovieDescription
        val btnExpandCollapse = binding.btnExpandCollapse

        btnExpandCollapse.setOnClickListener {
            val isExpanded = tvDescription.maxLines == Integer.MAX_VALUE
            val newMaxLines = if(isExpanded) 3 else Integer.MAX_VALUE
            val buttonText = if(isExpanded) getString(R.string.btn_expand)
            else getString(R.string.btn_collapse)

            TransitionManager.beginDelayedTransition(binding.root as ViewGroup, ChangeBounds().apply {
                duration = 200
            })

            tvDescription.maxLines = newMaxLines
            btnExpandCollapse.text = buttonText
        }
    }

    private fun initTrailersList(trailers: List<Trailer>) {
        val trailerAdapter = TrailerAdapter(trailers)
        binding.rvTrailers.apply {
            adapter = trailerAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        trailerAdapter.listener = { videoUrl ->
            videoUrl?.let {trailerPlayListener?.onTrailerClicked(it)}
        }
    }


    private fun initMovieStaffList(persons: List<Person>) {
        val movieStaff = persons.filter {
            it.enProfession != "actor"
        }
        val movieStaffAdapter = MovieStaffAdapter(movieStaff)
        movieStaffAdapter.listener = {personId ->
            val bundle = bundleOf(KEY_BUNDLE_PERSON to personId)
            (activity as Navigator).navigateToPersonDetailsFragment(bundle)
        }
        binding.rvProductionStaff.apply {
            adapter = movieStaffAdapter
            layoutManager = getCustomLayoutManager()
        }
    }

    private fun getCustomLayoutManager(): LinearLayoutManager {
        return object : LinearLayoutManager(requireContext(), HORIZONTAL, false) {
            override fun smoothScrollToPosition(recyclerView: RecyclerView?, state: RecyclerView.State?, position: Int) {
                val smoothScroller = object : LinearSmoothScroller(requireContext()) {
                    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                        return MILLISECONDS_PER_INCH / displayMetrics.densityDpi
                    }

                    override fun getVerticalSnapPreference(): Int {
                        return SNAP_TO_START
                    }

                    override fun getHorizontalSnapPreference(): Int {
                        return SNAP_TO_START
                    }
                }
                smoothScroller.targetPosition = position
                startSmoothScroll(smoothScroller)
            }
        }
    }

    private fun initGenresChip(genres: List<Genre>?) {
        binding.genresChips.removeAllViews()
        genres?.forEach { genre ->
            val chip = Chip(requireContext()).apply {
                text = genre.name
                chipBackgroundColor = ContextCompat
                    .getColorStateList(requireContext(), com.test.application.core.R.color.black)
                setTextColor(ContextCompat.getColor(requireContext(), com.test.application.core.R.color.white))
                chipStrokeWidth = resources.getDimension(R.dimen.chip_stroke_width)
                chipStrokeColor = ContextCompat
                    .getColorStateList(requireContext(),R.color.chip_stroke_color)
            }
            binding.genresChips.addView(chip)
        }
    }

    private fun displayTextInfo(movie: MovieDetails) {
        with(binding) {
            val movieSlogan = movie.slogan?.let {"\"$it\""} ?: ""
            tvMovieSlogan.text = movieSlogan

            tvMovieDescription.text = movie.description

            tvMovieReleaseDate.text = reformatPremiereDate(
                requireContext(), movie.premiere?.world.toString())

            tvMovieLength.text = if(movie.movieLength?.let { it > 0 } == true) {
                getString(R.string.format_movie_length, movie.movieLength.toString())
            } else { "" }

            tvMovieCountry.text = movie.countries?.convert { country -> country.name }

            tvMovieBudget.text = reformatBudget(requireContext(), movie.budget)
        }
    }
}