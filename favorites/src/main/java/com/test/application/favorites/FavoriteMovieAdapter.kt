package com.test.application.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.application.core.domain.favorites.FavoriteMovieItem
import com.test.application.favorites.databinding.ItemFavoriteMovieBinding
import java.util.*

class FavoriteMovieAdapter(
    private var movieData: List<FavoriteMovieItem>
) : RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder>() {

    var listener: ((id: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFavoriteMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int = movieData.size

    inner class ViewHolder(
        private val binding: ItemFavoriteMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: FavoriteMovieItem) {
            with(binding) {

                tvMovieTitle.text = movie.name

                tvMovieDescription.text = movie.description

                poster.load(movie.poster){
                    crossfade(true)
                    placeholder(com.test.application.core.R.drawable.default_placeholder)
                }

                val viewingDate = Date(movie.date)
                tvDate.text = viewingDate.toString()

                tvUserNote.text = movie.userNote

                root.setOnClickListener {
                    listener?.invoke(movie.kinopoiskId)
                }
            }
        }
    }
}