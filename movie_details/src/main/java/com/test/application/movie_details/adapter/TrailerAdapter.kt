package com.test.application.movie_details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.application.core.domain.movieDetail.Trailer
import com.test.application.movie_details.databinding.ItemTrailerBinding

class TrailerAdapter(
    private val trailers: List<Trailer>
) : RecyclerView.Adapter<TrailerAdapter.ViewHolder>() {

    var listener: ((url: String?) -> Unit)? = null
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(trailer: Trailer) {
            val binding = ItemTrailerBinding.bind(itemView)
            binding.tvTrailerName.text = trailer.name
            binding.root.setOnClickListener {
                listener?.invoke(trailer.url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTrailerBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount() = trailers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trailers[position])
    }
}