package com.test.application.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.application.contacts.databinding.ItemContactsBinding
import com.test.application.core.domain.contacts.ContactsItem

class ContactsAdapter : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    private var contacts: List<ContactsItem> = listOf()

    var listener: ((phoneNumber: String) -> Unit)? = null

    fun updateData(newContacts: List<ContactsItem>) {
        val diffCallback = DiffCallback(contacts, newContacts)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        contacts = newContacts
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactsBinding.inflate(LayoutInflater.from(parent.context), parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount() = contacts.size

    inner class ViewHolder(
        private val binding: ItemContactsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: ContactsItem) {
            with(binding) {
                tvContactName.text = contact.name
                tvContactPhoneNumber.text = contact.phoneNumber

                call.setOnClickListener {
                    contact.phoneNumber?.let { phoneNumber ->
                        listener?.invoke(phoneNumber) }
                }
            }
        }
    }
}