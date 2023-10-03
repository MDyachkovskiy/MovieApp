package com.example.kotlin_movieapp.view.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.kotlin_movieapp.model.datasource.domain.searchCollection.Doc

class SearchCollectionDiffCallback : DiffUtil.ItemCallback<Doc>() {
    override fun areItemsTheSame(oldItem: Doc, newItem: Doc): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Doc, newItem: Doc): Boolean {
        return oldItem == newItem
    }
}