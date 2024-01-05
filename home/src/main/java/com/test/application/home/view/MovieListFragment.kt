package com.test.application.home.view

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.application.core.navigation.Navigator
import com.test.application.core.utils.KEY_BUNDLE_MOVIE
import com.test.application.core.utils.KEY_BUNDLE_MOVIE_TYPE
import com.test.application.core.utils.MOVIE_LIST_TAG
import com.test.application.home.R
import com.test.application.home.adapter.MovieCollectionAdapter
import com.test.application.home.adapter.SpacingItemDecoration
import com.test.application.home.databinding.FragmentMovieListBinding
import com.test.application.home.util.MovieDataType
import com.test.application.home.util.RecyclerViewType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeSharedViewModel by viewModels({requireParentFragment()})

    private var movieAdapter: MovieCollectionAdapter? = null

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
        initializeAdapter()
        setupRecyclerView()
        observeMovieData(movieDataType)
        setupButtons()
    }

    private fun initializeAdapter() {
        val (itemWidth, itemHeight) = calculateItemWidth()
        movieAdapter = MovieCollectionAdapter(itemWidth, itemHeight).apply {
            setupItemClickListener()
        }
    }

    private fun setupButtons() {
        binding.backButton.setOnClickListener {
            Log.d("@@@", "MovieListFragment Back button clicked")
            parentFragmentManager.popBackStack(MOVIE_LIST_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE) }

        binding.listViewButton.setOnClickListener { switchToListLayout() }
        binding.gridViewButton.setOnClickListener { switchToGridLayout() }
    }

    private fun switchToGridLayout() {
        val (itemWidth, itemHeight) = calculateItemWidth()
        binding.movieListRV.layoutManager = GridLayoutManager(requireContext(), 3)
        recreateAdapter(itemWidth, itemHeight, RecyclerViewType.GRID)
        updateButtonVisibility(isListLayout = false)
        addItemDecoration()
    }

    private fun addItemDecoration() {
        val spacingInPixels = resources
            .getDimensionPixelSize(com.test.application.core.R.dimen.margin_8dp_small)
        binding.movieListRV.addItemDecoration(SpacingItemDecoration(spacingInPixels))
    }

    private fun switchToListLayout() {
        binding.movieListRV.layoutManager = LinearLayoutManager(requireContext())
        recreateAdapter(viewType = RecyclerViewType.LIST)
        updateButtonVisibility(isListLayout = true)
        removeItemDecoration()
    }

    private fun recreateAdapter(width: Int = -1, height: Int = -1, viewType: RecyclerViewType) {
        movieAdapter = MovieCollectionAdapter(width, height).apply {
            setViewType(viewType)
            setupItemClickListener()
        }
        binding.movieListRV.adapter = movieAdapter
        observeMovieData(movieDataType)
    }

    private fun removeItemDecoration() {
        while (binding.movieListRV.itemDecorationCount > 0) {
            binding.movieListRV.removeItemDecorationAt(0)
        }
    }

    private fun updateButtonVisibility(isListLayout: Boolean) {
        binding.listViewButton.visibility = if(isListLayout) View.GONE else View.VISIBLE
        binding.gridViewButton.visibility = if(isListLayout) View.VISIBLE else View.GONE
    }

    private fun setupRecyclerView() {
        binding.movieListRV.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = movieAdapter
            val spacingInPixels = resources
                .getDimensionPixelSize(com.test.application.core.R.dimen.margin_8dp_small)
            addItemDecoration(SpacingItemDecoration(spacingInPixels))
        }
    }

    private fun setupItemClickListener() {
        movieAdapter?.listener = { movieId ->
            val bundle = bundleOf(KEY_BUNDLE_MOVIE to movieId)
            (activity as Navigator).navigateToMovieDetailsFragment(bundle)
        }
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
        val liveData = when (movieDataType) {
            MovieDataType.TOP_250.name -> viewModel.top250LiveData
            MovieDataType.TOP_TV_SHOWS.name -> viewModel.topTvShowsLiveData
            MovieDataType.UPCOMING_MOVIES.name -> viewModel.upComingLiveData
            else -> throw IllegalArgumentException(getString(R.string.incorrect_movie_data))
        }
        liveData.removeObservers(viewLifecycleOwner)
        liveData.observe(viewLifecycleOwner) { pagingData ->
            movieAdapter?.submitData(viewLifecycleOwner.lifecycle, pagingData)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (parentFragment as HomeFragment).restoreScrollView()
        _binding = null
    }
}