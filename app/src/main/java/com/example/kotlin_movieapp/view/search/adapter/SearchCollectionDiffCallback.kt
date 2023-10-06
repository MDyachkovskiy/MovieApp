package com.example.kotlin_movieapp.view.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.kotlin_movieapp.model.datasource.domain.searchCollection.Movie

class SearchCollectionDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}