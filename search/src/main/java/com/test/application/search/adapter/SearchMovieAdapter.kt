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

    var listener: (() -> Unit)? = null
    inner class ViewHolder(
        binding: ItemSearchMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: SearchMovie) {
            val binding = ItemSearchMovieBinding.bind(itemView)
            with(binding) {

                movieTitle.text = movie.name

                movieDescription.text = movie.description

                poster.load(movie.poster) {
                    crossfade(true)
                    placeholder(com.test.application.core.R.drawable.default_placeholder)
                }

                root.setOnClickListener {
                    listener?.invoke()
                }
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

/*
it.openDetailsFragment(
                        MovieDetailsFragment::class.java,
                        KEY_BUNDLE_MOVIE,
                        movie)
 */