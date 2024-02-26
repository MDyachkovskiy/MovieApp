package com.test.application.movie_details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.application.core.domain.movieDetail.Person
import com.test.application.movie_details.databinding.ItemMovieStaffBinding

class MovieStaffAdapter(
    private var personsData: List<Person>
    ) : RecyclerView.Adapter<MovieStaffAdapter.ViewHolder>() {

    var listener: ((personId: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieStaffBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(personsData[position])
    }

    override fun getItemCount() = personsData.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(person: Person) {
            val binding = ItemMovieStaffBinding.bind(itemView)
            with(binding) {

                personPoster.load(person.photo){
                    crossfade(true)
                    placeholder(com.test.application.core.R.drawable.person_placeholder)
                }

                tvPersonName.text = person.name
                tvPersonProfession.text = person.enProfession

                root.setOnClickListener {
                    listener?.invoke(person.id)
                }
            }
        }
    }
}