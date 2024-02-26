package com.test.application.movie_details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.application.core.domain.movieDetail.Facts
import com.test.application.movie_details.databinding.ItemCommentsBinding

class CommentsAdapter(
    private var facts: List<Facts>
) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCommentsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(facts[position])
    }

    override fun getItemCount() = facts.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(facts: Facts) {
            val binding = ItemCommentsBinding.bind(itemView)
            with(binding) {
                tvMovieFacts.text = facts.value
            }
        }
    }
}