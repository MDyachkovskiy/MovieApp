package com.test.application.movie_details.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.application.core.domain.movieDetail.MovieDetails
import com.test.application.core.domain.movieDetail.Person
import com.test.application.core.navigation.Navigator
import com.test.application.core.utils.AppState.AppState
import com.test.application.core.utils.KEY_BUNDLE_PERSON
import com.test.application.core.utils.init
import com.test.application.core.view.BaseFragmentWithAppState
import com.test.application.movie_details.adapter.ActorsAdapter
import com.test.application.movie_details.databinding.FragmentMovieCastBinding

class MovieCastFragment : BaseFragmentWithAppState<AppState, MovieDetails, FragmentMovieCastBinding>(
    FragmentMovieCastBinding::inflate
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
        initActorsRecyclerView(data.persons)
    }

    private fun initActorsRecyclerView(persons: List<Person>) {
        val actors = persons.filter {
            it.enProfession == "actor"
        }
        val actorsAdapter = ActorsAdapter(actors)
        actorsAdapter.listener = { personId ->
            val bundle = bundleOf(KEY_BUNDLE_PERSON to personId)
            (activity as Navigator).navigateToPersonDetailsFragment(bundle)
        }
        binding.rvActors.init(actorsAdapter, LinearLayoutManager.VERTICAL)
    }
}