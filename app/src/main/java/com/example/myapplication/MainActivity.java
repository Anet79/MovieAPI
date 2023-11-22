package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //link for example API request
    //https://api.themoviedb.org/3/movie/550?api_key=8f6bf8e36491dd768b466b1b91f3db48


    public static final String NEXT_SCREEN = "details_screen";

    //link for popular movies
    private static String JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key=8f6bf8e36491dd768b466b1b91f3db48";
    private static String API_KEY = "8f6bf8e36491dd768b466b1b91f3db48";

    private static String CATEGORY = "top_rated";
    List<MovieModelClass> movieList;

   List<MovieModelClass> movieListToSearch;

    private SearchView search_view;
    private MovieAdapter movieAdapter;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();

        recyclerView = findViewById(R.id.RecyclerViewMovies);
        search_view = findViewById(R.id.search_view);
        search_view.clearFocus();
        putAllTheMovie();

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchMovies1(newText);
                //searchMovies(listener,Integer.valueOf(query));
                if(movieListToSearch.isEmpty()){
                    Toast.makeText(MainActivity.this, "Dont Have this Movie!", Toast.LENGTH_SHORT).show();

                }
                else {
                   // movieAdapter= new MovieAdapter(MainActivity.this,movieListToSearch);
                   movieAdapter.setFilterListItems(movieListToSearch);

                }
                return true;
            }
        });




    }

    private final OnSearchApiListener listener = new OnSearchApiListener() {
        @Override
        public void onResponse(RetrofitResponse retrofitResponse) {
            if (retrofitResponse == null) {
                return;
            }

            movieListToSearch = new ArrayList<>(Arrays.asList(retrofitResponse.getMovies()));
            putDataIntoRecyclerView(movieListToSearch);

        }
    };

    public void searchMovies1(String query){
        movieListToSearch = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            if(movieList.get(i).name.toLowerCase().contains(query.toLowerCase())){
                movieListToSearch.add(movieList.get(i));
            }
        }

    }

    public void searchMovies(OnSearchApiListener listener, int movieId) {
        Call<RetrofitResponse> call = Manager.movieApi.getSpecificMovie(movieId, API_KEY);
        call.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<RetrofitResponse> call, Throwable t) {

            }
        });


    }

    private void putAllTheMovie() {
        Call<RetrofitResponse> call1 = Manager.movieApi.getMovies(CATEGORY,API_KEY ,null,null);


        call1.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {
                RetrofitResponse retrofitResponse = response.body();
                movieList = new ArrayList<>(Arrays.asList(retrofitResponse.getMovies()));
                putDataIntoRecyclerView(movieList);

            }

            @Override
            public void onFailure(Call<RetrofitResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    private void putSpecificMovie(int movieId) {
        Call<MovieModelClass> call = Manager.movieApi.getSpecificMovie1(movieId, API_KEY);
        call.enqueue(new Callback<MovieModelClass>() {
            @Override
            public void onResponse(Call<MovieModelClass> call, Response<MovieModelClass> response) {
                //movieModelClass = new MovieModelClass();
                MovieModelClass movieModelClass = response.body();
                Intent intent = new Intent(MainActivity.this, MovieDetails.class);
                intent.putExtra(NEXT_SCREEN, movieModelClass);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<MovieModelClass> call, Throwable t) {

            }
        });

    }

    private void putDataIntoRecyclerView(List<MovieModelClass> movieList) {


        Collections.sort(movieList,new SortItems());

         movieAdapter = new MovieAdapter(this, movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(movieAdapter);


        movieAdapter.setMovieOnClickListener(new MovieOnClickListener() {
            @Override
            public void movieOnClicked(int position, MovieModelClass movieModelClass) {
                Intent intent = new Intent(MainActivity.this, MovieDetails.class);
                intent.putExtra(NEXT_SCREEN, movieModelClass);
                startActivity(intent);
            }
        });


    }


}