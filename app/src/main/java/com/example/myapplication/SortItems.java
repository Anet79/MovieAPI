package com.example.myapplication;

import java.util.Comparator;

public class SortItems implements Comparator<MovieModelClass> {
    @Override
    public int compare(MovieModelClass movieModelClass, MovieModelClass t1) {
        return t1.getReleaseDate().compareTo(movieModelClass.getReleaseDate());
    }
}
