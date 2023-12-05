package com.test.application.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.test.application.core.domain.searchCollection.SearchMovie

class SearchCollectionDiffCallback : DiffUtil.ItemCallback<SearchMovie>() {

    override fun areItemsTheSame(oldItem: SearchMovie, newItem: SearchMovie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SearchMovie, newItem: SearchMovie): Boolean {
        return oldItem == newItem
    }
}