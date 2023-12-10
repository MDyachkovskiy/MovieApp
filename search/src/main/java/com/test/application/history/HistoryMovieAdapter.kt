package com.test.application.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.application.core.domain.history.HistoryMovieItem
import com.test.application.search.databinding.ItemHistoryMovieBinding
import java.text.SimpleDateFormat
import java.util.*

class HistoryMovieAdapter : RecyclerView.Adapter<HistoryMovieAdapter.ViewHolder>() {

    private var historyList: List<HistoryMovieItem> = listOf()
    var listener: ((id: Int) -> Unit)? = null

    fun update(newList: List<HistoryMovieItem>) {
        val diffCallback = HistoryAdapterDiffCallback(historyList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        historyList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryMovieBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(historyList[position])
    }

    override fun getItemCount() = historyList.size

    inner class ViewHolder(
        private val binding: ItemHistoryMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: HistoryMovieItem) {
            initTextData(movie)
            initImage(movie)
            initClickListener(movie)
        }

        private fun initClickListener(movie: HistoryMovieItem) {
            binding.root.setOnClickListener {
                listener?.invoke(movie.kinopoiskId)
            }
        }

        private fun initImage(movie: HistoryMovieItem) {
            binding.poster.load(movie.poster) {
                crossfade(true)
                placeholder(com.test.application.core.R.drawable.default_placeholder)
            }
        }

        private fun initTextData(movie: HistoryMovieItem) {
            with(binding) {
                tvMovieTitle.text = movie.name
                tvMovieDescription.text = movie.description

                val viewingDate = Date(movie.date)
                val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
                tvDate.text = formatter.format(viewingDate)

                tvUserNote.text = movie.userNote
            }
        }
    }
}