package com.example.kotlin_movieapp.ui.main.favoritesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin_movieapp.databinding.MovieListFragmentBinding

class FavoritesListFragment : Fragment() {

    private var _binding: MovieListFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = FavoritesListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = MovieListFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}