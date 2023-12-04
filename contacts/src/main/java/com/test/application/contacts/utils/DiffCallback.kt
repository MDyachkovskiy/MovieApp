package com.test.application.contacts.utils

import androidx.recyclerview.widget.DiffUtil
import com.test.application.core.domain.contacts.ContactsItem

class DiffCallback(
    private val oldList: List<ContactsItem>,
    private val newList: List<ContactsItem>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}