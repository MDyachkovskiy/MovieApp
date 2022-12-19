package com.example.kotlin_movieapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_movieapp.adapters.MovieAdapter
import com.example.kotlin_movieapp.databinding.FragmentMainBinding
import com.example.kotlin_movieapp.models.MovieSource
import com.example.kotlin_movieapp.models.MovieSourceImpl
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private lateinit var movieAdapter : MovieAdapter
    private var _binding : FragmentMainBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var data : MovieSource

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)

        initRV()

        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val observer = object : Observer<AppState> {
            override fun onChanged(appState : AppState) {
                renderData(appState)
            }
        }

        viewModel.getData().observe(viewLifecycleOwner, observer)
        viewModel.getMovie()

    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun renderData(appState : AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.main,
                    "Возникла ошибка загрузки данных",
                    Snackbar.LENGTH_LONG)
                    .show()
            }

            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }

            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
            }
        }
    }


    private fun initRV() {

        this.data = MovieSourceImpl(resources).init()

        movieAdapter = MovieAdapter(data)

        binding.RVTopMovies.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        binding.RVSerials.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        binding.RVNewMovies.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }
}