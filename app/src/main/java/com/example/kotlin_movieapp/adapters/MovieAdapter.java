package com.example.kotlin_movieapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kotlin_movieapp.R;
import com.example.kotlin_movieapp.models.Movie;
import com.example.kotlin_movieapp.models.MovieSource;

public class MovieAdapter extends RecyclerView.Adapter <MovieAdapter.ViewHolder> {

    private final MovieSource movieSource;

    public MovieAdapter(MovieSource movieSource){
        this.movieSource = movieSource;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new MovieAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(movieSource.getMovie(position));
    }

    @Override
    public int getItemCount() {
        return movieSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView poster;
        private final TextView tvMovieName;

        public ViewHolder(View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.moviePoster);
            tvMovieName = itemView.findViewById(R.id.movieName);

        }

        public void setData(Movie movie) {
            poster.setImageResource(movie.getImage());
            tvMovieName.setText(movie.getName());
        }
    }
}