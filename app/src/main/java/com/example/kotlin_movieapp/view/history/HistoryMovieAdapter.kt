package com.example.kotlin_movieapp.view.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_movieapp.databinding.ItemHistoryMovieBinding
import com.test.application.local_data.history.HistoryMovieItem
import com.test.application.core.utils.KEY_BUNDLE_MOVIE
import com.example.kotlin_movieapp.utils.convertHistoryMovieItemToMovie
import com.test.application.core.utils.openDetailsFragment
import com.example.kotlin_movieapp.view.movieDetails.MovieDetailsFragment
import com.squareup.picasso.Picasso
import java.util.*

class HistoryMovieAdapter(
    private var movieData: List<com.test.application.local_data.history.HistoryMovieItem>
) : RecyclerView.Adapter<HistoryMovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryMovieBinding.inflate(
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

        fun bind(movie: com.test.application.local_data.history.HistoryMovieItem) {
            val binding = ItemHistoryMovieBinding.bind(itemView)
            with(binding) {

                movieTitle.text = movie.name

                movieDescription.text = movie.description

                Picasso.get()?.load(movie.poster)?.into(poster)

                val viewingDate = Date(movie.date)
                date.text = viewingDate.toString()

                userNote.text = movie.userNote

                root.setOnClickListener {
                    it.openDetailsFragment(
                        MovieDetailsFragment::class.java,
                        KEY_BUNDLE_MOVIE,
                        convertHistoryMovieItemToMovie(movie)
                    )
                }
            }
        }
    }
}