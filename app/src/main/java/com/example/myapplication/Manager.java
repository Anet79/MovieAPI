package com.example.myapplication;

import android.app.Application;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Manager extends Application {

    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //instance for interface
     static MovieApi movieApi= retrofit.create(MovieApi.class);



}
