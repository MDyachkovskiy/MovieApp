package com.test.application.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.application.core.domain.collection.Movie
import coil.load
import com.test.application.core.utils.convert
import com.test.application.home.R
import com.test.application.home.databinding.ItemMovieBinding
import com.test.application.home.databinding.ListItemMovieBinding
import com.test.application.home.util.RecyclerViewType

class MovieCollectionAdapter(
    private val itemWidth: Int = -1,
    private val itemHeight: Int = -1
) :
    PagingDataAdapter<Movie, RecyclerView.ViewHolder>(MovieCollectionDiffCallback()) {

    var listener: ((movieId: Int) -> Unit)? = null

    private var viewType = RecyclerViewType.GRID

    fun setViewType(viewType: RecyclerViewType) {
        this.viewType = viewType
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            when(holder) {
                is GridViewHolder -> holder.bind(movie)
                is ListViewHolder -> holder.bind(movie)
                is HorizontalListViewHolder -> holder.bind(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(this.viewType) {
            RecyclerViewType.LIST -> {
                val binding = ListItemMovieBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)

                val itemHeight = parent.resources.getDimensionPixelSize(R.dimen.list_item_movie_height)

                binding.root.layoutParams = RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, itemHeight)
                ListViewHolder(binding)
            }
            RecyclerViewType.GRID -> {
                val binding = ItemMovieBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                binding.root.layoutParams = RecyclerView.LayoutParams(itemWidth, itemHeight)
                GridViewHolder(binding)
            }
            RecyclerViewType.HORIZONTAL_LIST -> {
                val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HorizontalListViewHolder(binding)
            }
        }
    }

    inner class GridViewHolder(
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

    inner class ListViewHolder(
        private val binding: ListItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                moviePoster.load(movie.poster.previewUrl) {
                    crossfade(true)
                    placeholder(com.test.application.core.R.drawable.default_placeholder)
                }

                tvMovieTitle.text = movie.name
                tvMovieReleaseYear.text = movie.year
                tvMovieGenre.text = movie.genres?.convert { genre -> genre.name }

                tvRating.text = movie.rating.imdb.toString()
            }
        }
    }

    inner class HorizontalListViewHolder(
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

