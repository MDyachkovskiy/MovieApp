package com.test.application.home.view

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.test.application.core.navigation.Navigator
import com.test.application.core.utils.KEY_BUNDLE_MOVIE
import com.test.application.core.utils.KEY_BUNDLE_MOVIE_TYPE
import com.test.application.home.R
import com.test.application.home.adapter.MovieCollectionAdapter
import com.test.application.home.adapter.SpacingItemDecoration
import com.test.application.home.databinding.FragmentMovieListBinding
import com.test.application.home.util.MovieDataType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeSharedViewModel by viewModels({requireParentFragment()})

    private lateinit var movieAdapter: MovieCollectionAdapter

    private val movieDataType: String by lazy {
        arguments?.getString(KEY_BUNDLE_MOVIE_TYPE) ?:
        throw IllegalArgumentException(getString(R.string.incorrect_movie_data))
    }

    companion object {
        fun newInstance(bundle: Bundle) = MovieListFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeMovieData(movieDataType)
    }

    private fun setupRecyclerView() {
        val (itemWidth, itemHeight) = calculateItemWidth()
        initializeRecyclerView(itemWidth, itemHeight)
        setupItemClickListener()
    }

    private fun setupItemClickListener() {
        movieAdapter.listener = { movieId ->
            val bundle = bundleOf(KEY_BUNDLE_MOVIE to movieId)
            (activity as Navigator).navigateToMovieDetailsFragment(bundle)
        }
    }

    private fun initializeRecyclerView(itemWidth: Int, itemHeight: Int) {
        movieAdapter = MovieCollectionAdapter(itemWidth, itemHeight)
        binding.movieListRV.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.movieListRV.adapter = movieAdapter
        val spacingInPixels = resources
            .getDimensionPixelSize(com.test.application.core.R.dimen.margin_8dp_small)
        binding.movieListRV.addItemDecoration(SpacingItemDecoration(spacingInPixels))
    }

    private fun calculateItemWidth(): Pair<Int, Int> {
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        val spacingInPixels = resources
            .getDimensionPixelSize(com.test.application.core.R.dimen.margin_10dp_small)

        val originalWidthDp = resources.getDimensionPixelSize(R.dimen.item_movie_width)
        val originalHeightDp = resources.getDimensionPixelSize(R.dimen.item_movie_height)

        val itemWidth = (screenWidth - (4 * spacingInPixels)) / 3
        val itemHeight = (itemWidth * originalHeightDp) / originalWidthDp
        return Pair(itemWidth, itemHeight)
    }

    private fun observeMovieData(movieDataType: String) {
        when(movieDataType){
            MovieDataType.TOP_250.name -> {
                viewModel.top250LiveData.observe(viewLifecycleOwner){ pagingData ->
                    movieAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
                }
            }
            MovieDataType.TOP_TV_SHOWS.name -> {
                viewModel.topTvShowsLiveData.observe(viewLifecycleOwner){ pagingData ->
                    movieAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
                }
            }
            MovieDataType.UPCOMING_MOVIES.name -> {
                viewModel.upComingLiveData.observe(viewLifecycleOwner){ pagingData ->
                    movieAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (parentFragment as HomeFragment).restoreScrollView()
        _binding = null
    }
}