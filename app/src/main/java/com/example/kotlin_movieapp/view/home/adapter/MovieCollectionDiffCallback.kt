package com.example.kotlin_movieapp.view.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.kotlin_movieapp.model.datasource.domain.collection.Movie

class MovieCollectionDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}