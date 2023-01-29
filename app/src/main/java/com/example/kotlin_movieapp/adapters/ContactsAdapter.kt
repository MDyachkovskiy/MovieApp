package com.example.kotlin_movieapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_movieapp.databinding.ItemContactsBinding
import com.example.kotlin_movieapp.model.room.contacts.ContactsItem

class ContactsAdapter (
    private var contactsData: List<ContactsItem>
        ) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactsBinding.inflate(LayoutInflater.from(parent.context),
        parent,
        false)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contactsData[position])
    }

    override fun getItemCount(): Int = contactsData.size

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(contact: ContactsItem) {
            val binding = ItemContactsBinding.bind(itemView)
            with(binding) {
                contactName.text = contact.name
                contactPhoneNumber.text = contact.phoneNumber
            }
        }
    }
}