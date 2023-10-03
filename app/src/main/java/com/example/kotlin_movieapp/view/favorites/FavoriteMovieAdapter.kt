package com.example.kotlin_movieapp.view.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_movieapp.databinding.ItemFavoriteMovieBinding
import com.example.kotlin_movieapp.model.datasource.local.room.favorites.FavoriteMovieItem
import com.example.kotlin_movieapp.utils.KEY_BUNDLE_MOVIE
import com.example.kotlin_movieapp.utils.convertFavoriteMovieItemToCollectionItem
import com.example.kotlin_movieapp.utils.openDetailsFragment
import com.example.kotlin_movieapp.view.movieDetails.MovieDetailsFragmentWithAppState
import com.squareup.picasso.Picasso
import java.util.*

class FavoriteMovieAdapter(
    private var movieData: List<FavoriteMovieItem>
) : RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFavoriteMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int = movieData.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: FavoriteMovieItem) {
            val binding = ItemFavoriteMovieBinding.bind(itemView)
            with(binding) {

                movieTitle.text = movie.name

                movieDescription.text = movie.description

                Picasso.get()?.load(movie.poster)?.into(poster)

                val viewingDate = Date(movie.date)
                date.text = viewingDate.toString()

                userNote.text = movie.userNote

                root.setOnClickListener {
                    it.openDetailsFragment(
                        MovieDetailsFragmentWithAppState::class.java,
                        KEY_BUNDLE_MOVIE,
                        convertFavoriteMovieItemToCollectionItem(movie)
                    )
                }
            }
        }
    }
}