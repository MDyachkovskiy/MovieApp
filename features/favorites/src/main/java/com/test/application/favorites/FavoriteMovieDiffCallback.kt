package com.test.application.favorites

import androidx.recyclerview.widget.DiffUtil
import com.test.application.core.domain.favorites.FavoriteMovieItem

class FavoriteMovieDiffCallback(
    private val oldList: List<FavoriteMovieItem>,
    private val newList: List<FavoriteMovieItem>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].kinopoiskId == newList[newItemPosition].kinopoiskId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}