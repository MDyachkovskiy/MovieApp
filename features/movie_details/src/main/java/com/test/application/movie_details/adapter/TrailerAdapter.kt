package com.test.application.movie_details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.application.core.domain.movieDetail.Trailer
import com.test.application.movie_details.R
import com.test.application.movie_details.databinding.ItemTrailerBinding
import com.test.application.movie_details.utils.extractVideoIdFromUrl

class TrailerAdapter(
    private val trailers: List<Trailer>
) : RecyclerView.Adapter<TrailerAdapter.ViewHolder>() {

    var listener: ((url: String?) -> Unit)? = null
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(trailer: Trailer) {
            val binding = ItemTrailerBinding.bind(itemView)
            binding.tvTrailerName.text = trailer.name

            val videoId = trailer.url?.let { extractVideoIdFromUrl(it) }
            val thumbnailUrl = itemView.context.getString(R.string.youtube_thumbnail_url, videoId)

            binding.imageViewThumbnail.load(thumbnailUrl){
                crossfade(true)
                placeholder(R.drawable.ic_youtube)
            }

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