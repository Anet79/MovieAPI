package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("3/movie/{category}")
    Call<RetrofitResponse>getMovies(
            @Path("category") String category,

            @Query("api_key") String apiKey,
             @Query("_sort") String sort,
            @Query("_order") String order
    );

    @GET("3/movie/popular")
    Call<RetrofitResponse> getPopularMovies();

    @GET("3/movie/{movie_id}")
    Call<RetrofitResponse>getSpecificMovie(
            @Path(("movie_id"))int movieId,
            @Query("api_key") String apiKey
    );
    @GET("3/movie/{movie_id}")
    Call<MovieModelClass>getSpecificMovie1(
            @Path(("movie_id"))int movieId,
            @Query("api_key") String apiKey
    );




}
