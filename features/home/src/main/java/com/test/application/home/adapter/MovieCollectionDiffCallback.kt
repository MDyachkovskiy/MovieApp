package com.test.application.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.test.application.core.domain.collection.Movie

class MovieCollectionDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}