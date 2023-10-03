package com.example.kotlin_movieapp.view.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.kotlin_movieapp.model.datasource.domain.collection.Doc

class MovieCollectionDiffCallback : DiffUtil.ItemCallback<Doc>() {
    override fun areItemsTheSame(oldItem: Doc, newItem: Doc): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Doc, newItem: Doc): Boolean {
        return oldItem == newItem
    }
}