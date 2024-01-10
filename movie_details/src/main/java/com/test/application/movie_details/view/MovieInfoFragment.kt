package com.test.application.movie_details.view

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.test.application.core.domain.movieDetail.Budget
import com.test.application.core.domain.movieDetail.Genre
import com.test.application.core.domain.movieDetail.MovieDetails
import com.test.application.core.domain.movieDetail.Person
import com.test.application.core.navigation.Navigator
import com.test.application.core.utils.AppState.AppState
import com.test.application.core.utils.KEY_BUNDLE_PERSON
import com.test.application.core.utils.convert
import com.test.application.core.view.BaseFragmentWithAppState
import com.test.application.movie_details.R
import com.test.application.movie_details.adapter.MovieStaffAdapter
import com.test.application.movie_details.databinding.FragmentMovieInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

@AndroidEntryPoint
class MovieInfoFragment : BaseFragmentWithAppState<AppState, MovieDetails, FragmentMovieInfoBinding>(
    FragmentMovieInfoBinding::inflate
) {

    private val viewModel: MovieDetailsViewModel by viewModels({requireParentFragment()})

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
                        val MILLISECONDS_PER_INCH = 50f
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
                    .getColorStateList(requireContext(),com.test.application.core.R.color.white)
            }
            binding.genresChips.addView(chip)
        }
    }

    private fun displayTextInfo(movie: MovieDetails) {
        with(binding) {
            val movieSlogan = movie.slogan?.let {"\"$it\""} ?: ""
            tvMovieSlogan.text = movieSlogan

            tvMovieDescription.text = movie.description

            tvMovieReleaseDate.text = reformatPremiereDate(movie.premiere?.world.toString())

            tvMovieLength.text = if(movie.movieLength?.let { it > 0 } == true) {
                getString(R.string.format_movie_length, movie.movieLength.toString())
            } else { "" }

            tvMovieCountry.text = movie.countries?.convert { country -> country.name }

            tvMovieBudget.text = reformatBudget(movie.budget)
        }
    }

    private fun reformatPremiereDate(dateStr: String): String {
        return try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.")
            val date = LocalDate.parse(dateStr, formatter)
            date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.getDefault()))
        } catch(e: DateTimeParseException) {
            getString(R.string.undefined_date)
        }
    }

    private fun reformatBudget(budget: Budget?): String {
        return if(budget != null && budget.value > 0) {
            val formattedValue = NumberFormat
                .getNumberInstance(Locale.getDefault()).format(budget.value)
            "$formattedValue ${budget.currency}"
        } else getString(R.string.undefined_budget)
    }
}