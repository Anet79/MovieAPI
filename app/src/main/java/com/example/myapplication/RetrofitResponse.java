package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetrofitResponse {
    @SerializedName("results")
   private MovieModelClass[] movies;

    public MovieModelClass[] getMovies() {
        return movies;
    }

    public void setMovies(MovieModelClass[] movies) {
        this.movies = movies;
    }
}
