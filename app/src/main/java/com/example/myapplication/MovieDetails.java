package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MovieDetails extends AppCompatActivity {

    private TextView txt_name;
    private TextView txt_id;

    private TextView date;
    private ImageView imageView_movie_photo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        init();






        MovieModelClass movie = null;
        // checking if the intent has extra
        if (getIntent().hasExtra(MainActivity.NEXT_SCREEN)) {
            // get the Serializable data model class with the details in it
            movie = (MovieModelClass) getIntent().getSerializableExtra(MainActivity.NEXT_SCREEN);
        }

        if(movie!=null){
            txt_id.setText(movie.getId());
            txt_name.setText(movie.getName());
            date.setText(movie.getReleaseDate());
            Glide.with(MovieDetails.this).load("https://image.tmdb.org/t/p/w500"+movie.getImg()).into(imageView_movie_photo);


        }



    }

    public void init(){

        txt_id = findViewById(R.id.txt_id);
        txt_name = findViewById(R.id.txt_name);
        imageView_movie_photo = findViewById(R.id.imageView_movie_photo);
        date = findViewById(R.id.date);

    }



}
