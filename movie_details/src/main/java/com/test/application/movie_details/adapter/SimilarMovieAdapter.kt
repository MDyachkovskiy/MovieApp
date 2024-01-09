package com.test.application.movie_details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.application.core.domain.movieDetail.SimilarMovy
import com.test.application.movie_details.databinding.ItemSimilarMovieBinding

class SimilarMovieAdapter(
    private val similarMovies: List<SimilarMovy>
) : RecyclerView.Adapter<SimilarMovieAdapter.ViewHolder>() {

    var listener: ((personId: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSimilarMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(similarMovies[position])
    }

    override fun getItemCount() = similarMovies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(similarMovy: SimilarMovy) {
            val binding = ItemSimilarMovieBinding.bind(itemView)
            with(binding) {

                moviePoster.load(similarMovy.movieDetailsPoster.url){
                    crossfade(true)
                    placeholder(com.test.application.core.R.drawable.person_placeholder)
                }

                tvMovieTitle.text = similarMovy.name
                tvRating.text = similarMovy.rating.imdb.toString()

                root.setOnClickListener {
                    listener?.invoke(similarMovy.id)
                }
            }
        }
    }
}