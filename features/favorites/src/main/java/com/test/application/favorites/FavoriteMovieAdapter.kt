package com.test.application.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.application.core.domain.favorites.FavoriteMovieItem
import com.test.application.favorites.databinding.ItemFavoriteMovieBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FavoriteMovieAdapter : RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder>() {

    private var favoriteMovieList: MutableList<FavoriteMovieItem> = mutableListOf()

    var listener: ((id: Int) -> Unit)? = null
    var onFavoriteChanged: ((movieId: Int, isFavorite: Boolean ) -> Unit)? = null

    fun updateMovie(newMovies: List<FavoriteMovieItem>) {
        val diffCallback = FavoriteMovieDiffCallback(favoriteMovieList, newMovies)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        favoriteMovieList.clear()
        favoriteMovieList.addAll(newMovies)
        diffResult.dispatchUpdatesTo(this)
    }

    fun removeItem(movieId: Int) {
        val index = favoriteMovieList.indexOfFirst{ it.kinopoiskId == movieId}
        if (index != -1) {
            favoriteMovieList.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFavoriteMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favoriteMovieList[position])
    }

    override fun getItemCount(): Int = favoriteMovieList.size

    inner class ViewHolder(
        private val binding: ItemFavoriteMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: FavoriteMovieItem) {
            with(binding) {

                tvMovieTitle.text = movie.name

                tvMovieDescription.text = movie.description

                poster.load(movie.poster) {
                    crossfade(true)
                    placeholder(com.test.application.core.R.drawable.default_placeholder)
                }

                val viewingDate = Date(movie.date)
                val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
                tvDate.text = formatter.format(viewingDate)

                tvUserNote.text = movie.userNote

                root.setOnClickListener {
                    listener?.invoke(movie.kinopoiskId)
                }
            }
        }
    }
}