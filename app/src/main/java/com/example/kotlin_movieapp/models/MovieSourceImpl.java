package com.example.kotlin_movieapp.models;

import android.content.res.Resources;
import android.content.res.TypedArray;

import androidx.annotation.NonNull;

import com.example.kotlin_movieapp.R;

import java.util.ArrayList;
import java.util.List;

public class MovieSourceImpl implements MovieSource {

    private static List<Movie> movieSource;
    private final Resources resources;

    public MovieSourceImpl(Resources resources) {
        this.resources = resources;
        movieSource = new ArrayList<>(7);
    }

    public MovieSourceImpl init() {
        String[] movieNames = resources.getStringArray(R.array.MovieNames);
        int[] pictures = getImageArray();

        for (int i = 0; i < 7; i++) {
            movieSource.add(new Movie(i, pictures[i], movieNames[i]));
        }

        return this;

    }

    private int[] getImageArray() {
        TypedArray pictures = resources.obtainTypedArray(R.array.MoviePosters);
        int length = pictures.length();
        int[] answer = new int[length];
        for (int i = 0; i < length; i++) {
            answer[i] = pictures.getResourceId(i, 0);
        }
        return answer;
    }

    @NonNull
    @Override
    public Movie getMovie(int position) {
        return movieSource.get(position);
    }

    @Override
    public int size() {
        return movieSource.size();
    }

    @Override
    public int indexOf(@NonNull Movie movie) {
        return movieSource.indexOf(movie);
    }
}
