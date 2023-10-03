package com.example.kotlin_movieapp.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_movieapp.databinding.ItemMovieBinding
import com.example.kotlin_movieapp.model.datasource.domain.collection.Doc
import com.example.kotlin_movieapp.utils.KEY_BUNDLE_MOVIE
import com.example.kotlin_movieapp.utils.openDetailsFragment
import com.example.kotlin_movieapp.view.movieDetails.MovieDetailsFragmentWithAppState
import com.squareup.picasso.Picasso

class MovieCollectionAdapter :
    PagingDataAdapter<Doc, MovieCollectionAdapter.ViewHolder>(MovieCollectionDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) holder.bind(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Doc) {
            with(binding) {
                Picasso.get()?.load(movie.poster.previewUrl)?.into(moviePoster)

                root.setOnClickListener {
                    it.openDetailsFragment(
                        MovieDetailsFragmentWithAppState::class.java,
                        KEY_BUNDLE_MOVIE,
                        movie
                    )}
            }
        }
    }
}