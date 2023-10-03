package com.example.kotlin_movieapp.view.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_movieapp.databinding.ItemSearchMovieBinding
import com.example.kotlin_movieapp.model.datasource.domain.searchCollection.Doc
import com.example.kotlin_movieapp.utils.KEY_BUNDLE_MOVIE
import com.example.kotlin_movieapp.utils.openDetailsFragment
import com.example.kotlin_movieapp.view.movieDetails.MovieDetailsFragmentWithAppState
import com.squareup.picasso.Picasso

class SearchMovieAdapter :
    PagingDataAdapter<Doc, SearchMovieAdapter.ViewHolder>(SearchCollectionDiffCallback()) {
    class ViewHolder(
        binding: ItemSearchMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Doc) {
            val binding = ItemSearchMovieBinding.bind(itemView)
            with(binding) {

                movieTitle.text = movie.name

                movieDescription.text = movie.description

                Picasso.get()?.load(movie.poster)?.into(poster)

                root.setOnClickListener {
                    it.openDetailsFragment(
                        MovieDetailsFragmentWithAppState::class.java,
                        KEY_BUNDLE_MOVIE,
                        movie)}
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