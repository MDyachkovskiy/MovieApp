package com.example.kotlin_movieapp.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_movieapp.MainActivity
import com.example.kotlin_movieapp.R
import com.example.kotlin_movieapp.databinding.MovieItemBinding
import com.example.kotlin_movieapp.models.Movie
import com.example.kotlin_movieapp.ui.main.MovieDetailsFragment
import com.example.kotlin_movieapp.utils.KEY_BUNDLE_MOVIE

class MovieAdapter(private var movieData: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    fun setData (dataNew: List<Movie>){
        this.movieData = dataNew
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context),
                                                                    parent,
                                                                  false)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            val binding = MovieItemBinding.bind(itemView)
            binding.moviePoster.setImageResource(movie.image)
            binding.movieName.text = movie.name
            binding.root.setOnClickListener {
                val bundle = Bundle()
                bundle.putParcelable(KEY_BUNDLE_MOVIE, movie)
                (itemView.context as MainActivity).supportFragmentManager
                    .beginTransaction()
                    .addToBackStack("tag")
                    .add(R.id.container, MovieDetailsFragment.newInstance(bundle))
                    .commit()

            }        }

    }

}