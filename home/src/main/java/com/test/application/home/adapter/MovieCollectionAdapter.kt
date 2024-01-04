package com.test.application.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.application.core.domain.collection.Movie
import coil.load
import com.test.application.home.databinding.ItemMovieBinding

class MovieCollectionAdapter(
    private val itemWidth: Int = -1,
    private val itemHeight: Int = -1
) :
    PagingDataAdapter<Movie, MovieCollectionAdapter.ViewHolder>(MovieCollectionDiffCallback()) {

    var listener: ((movieId: Int) -> Unit)? = null


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) holder.bind(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        if(itemWidth > 0 && itemHeight > 0) {
            binding.root.layoutParams.width = itemWidth
            binding.root.layoutParams.height = itemHeight
        }

        return ViewHolder(binding)
    }

    inner class ViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {

                moviePoster.load(movie.poster.previewUrl) {
                    crossfade(true)
                    placeholder(com.test.application.core.R.drawable.default_placeholder)
                }

                movieTitle.text = movie.name

                tvRating.text = movie.rating.imdb.toString()

                root.setOnClickListener {
                    listener?.invoke(movie.id)
                }
            }
        }
    }
}