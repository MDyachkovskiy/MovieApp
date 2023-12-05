package com.test.application.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.application.core.domain.searchCollection.SearchMovie
import com.test.application.search.databinding.ItemSearchMovieBinding

class SearchMovieAdapter :
    PagingDataAdapter<SearchMovie, SearchMovieAdapter.ViewHolder>(SearchCollectionDiffCallback()) {

    var listener: ((movieId: Int) -> Unit)? = null
    inner class ViewHolder(
        private val binding: ItemSearchMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: SearchMovie) {
            initTextData(movie)
            initImages(movie)
            initRoomListener(movie)
        }

        private fun initRoomListener(movie: SearchMovie) {
            binding.root.setOnClickListener {
                listener?.invoke(movie.id)
            }
        }

        private fun initImages(movie: SearchMovie) {
            binding.poster.load(movie.poster) {
                crossfade(true)
                placeholder(com.test.application.core.R.drawable.default_placeholder)
            }
        }

        private fun initTextData(movie: SearchMovie) {
            with(binding) {
                tvMovieTitle.text = movie.name
                tvMovieDescription.text = movie.description
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) holder.bind(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
}