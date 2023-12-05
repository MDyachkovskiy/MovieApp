package com.test.application.history

import androidx.recyclerview.widget.DiffUtil
import com.test.application.core.domain.history.HistoryMovieItem

class HistoryAdapterDiffCallback(
    private val oldList: List<HistoryMovieItem>,
    private val newList: List<HistoryMovieItem>
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