package com.test.application.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.application.core.domain.favorites.FavoriteMovieItem
import com.test.application.core.navigation.Navigator
import com.test.application.favorites.databinding.ItemFavoriteMovieBinding
import java.util.*

class FavoriteMovieAdapter(
    private var movieData: List<FavoriteMovieItem>
) : RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder>() {

    var listener: (() -> Unit)? = null

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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: FavoriteMovieItem) {
            val binding = ItemFavoriteMovieBinding.bind(itemView)
            with(binding) {

                movieTitle.text = movie.name

                movieDescription.text = movie.description

                poster.load(movie.poster){
                    crossfade(true)
                    placeholder(com.test.application.core.R.drawable.default_placeholder)
                }

                val viewingDate = Date(movie.date)
                date.text = viewingDate.toString()

                userNote.text = movie.userNote

                root.setOnClickListener {
                    listener?.invoke()
                }
            }
        }
    }
}

/*it.openDetailsFragment(
                        MovieDetailsFragment::class.java,
                        KEY_BUNDLE_MOVIE,
                        convertFavoriteMovieItemToMovie(movie)
                    )*/